package com.jholdy.project.infrastructure

import software.amazon.awscdk.App
import software.amazon.awscdk.Duration
import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps
import software.amazon.awscdk.services.apigateway.LambdaIntegration
import software.amazon.awscdk.services.apigateway.RestApi
import software.amazon.awscdk.services.apigateway.RestApiProps
import software.amazon.awscdk.services.lambda.Architecture
import software.amazon.awscdk.services.lambda.Code
import software.amazon.awscdk.services.lambda.Function
import software.amazon.awscdk.services.lambda.FunctionProps
import software.amazon.awscdk.services.lambda.Runtime
import software.constructs.Construct
import java.nio.file.Paths

fun main() {
    // 1.
    val app = App()
    val stage: String = "dev"
    println("Current stage: $stage")
    LoanStack(app, "loan-stack", StackProps.builder().build(), stage)
    app.synth()
}

fun getProjectRoot(): String = "${Paths.get("").toAbsolutePath()}/../"

class LoanStack(
    parent: Construct,
    name: String,
    props: StackProps,
    stage: String,
) : Stack(parent, name, props) {
    init {
        // 2.
        val function =
            Function(
                this,
                "Function",
                FunctionProps
                    .builder()
                    .functionName("function-$stage")
                    .runtime(Runtime.JAVA_21)
                    .code(Code.fromAsset(getProjectRoot() + "lambda/build/libs/lambda-all.jar"))
                    .handler("com.jholdy.lambda.Handler::handleRequest")
                    .architecture(Architecture.ARM_64)
                    .memorySize(512)
                    .timeout(Duration.seconds(10))
                    .build(),
            )

        // 3.
        val restApi = RestApi(this, "Api", RestApiProps.builder().restApiName("api-$stage").build())
        val lambdaPath = restApi.root.addResource("{proxy+}")
        lambdaPath.addMethod(
            "GET",
            LambdaIntegration(function),
        )
    }
}
