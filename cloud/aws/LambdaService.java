import java.util.*;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.*;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaService {

    private final LambdaClient lambdaClient;

    public LambdaService(Region region) {
        this.lambdaClient = LambdaClient.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    // List all Lambda functions
    public List<String> listLambdas() {
        ListFunctionsResponse response = lambdaClient.listFunctions();
        return response.functions().stream()
                .map(FunctionConfiguration::functionName)
                .collect(Collectors.toList());
    }

    // Check if a Lambda function exists
    public boolean isLambdaAvailable(String lambdaName) {
        try {
            GetFunctionResponse response = lambdaClient.getFunction(GetFunctionRequest.builder()
                    .functionName(lambdaName)
                    .build());
            return response != null;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    // Enable a Lambda function (set concurrency > 0)
    public boolean enableLambda(String lambdaName) {
        try {
            // Set reserved concurrency to a positive value (e.g., 1)
            lambdaClient.putFunctionConcurrency(PutFunctionConcurrencyRequest.builder()
                    .functionName(lambdaName)
                    .reservedConcurrentExecutions(1)
                    .build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Disable a Lambda function (set concurrency to 0)
    public boolean disableLambda(String lambdaName) {
        try {
            lambdaClient.putFunctionConcurrency(PutFunctionConcurrencyRequest.builder()
                    .functionName(lambdaName)
                    .reservedConcurrentExecutions(0)
                    .build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Check if a Lambda function is enabled (concurrency != 0)
    public boolean isLambdaEnabled(String lambdaName) {
        try {
            GetFunctionConcurrencyResponse response = lambdaClient.getFunctionConcurrency(
                    GetFunctionConcurrencyRequest.builder().functionName(lambdaName).build());
            Integer reserved = response.reservedConcurrentExecutions();
            return reserved == null || reserved != 0;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    // Delete a Lambda function
    public boolean deleteLambda(String lambdaName) {
        try {
            lambdaClient.deleteFunction(DeleteFunctionRequest.builder()
                    .functionName(lambdaName)
                    .build());
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }
}