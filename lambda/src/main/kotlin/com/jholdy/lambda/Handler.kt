package com.jholdy.lambda

import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler.getAwsProxyHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import java.io.InputStream
import java.io.OutputStream

class Handler : RequestStreamHandler {
    private val handler = getAwsProxyHandler(Application::class.java)!!
    override fun handleRequest(input: InputStream?, output: OutputStream?, context: Context?) {
        handler.proxyStream(input, output, context)
    }
}