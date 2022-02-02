package com.freshworks.webserver;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class WebServerMain {

    private static Vertx vertx = Vertx.vertx();
//    EventBus eventBus = vertx.eventBus();


    public static void main(String args[]) {

        // Router verticle deployment options
        JsonObject routerConfig = new JsonObject();

        System.out.println("main | Command line arguments are given...");
        for (String argument : args) {
            System.out.println("main | Argument " + argument);
        }

        // Commandline Argument: Port
        if (args.length == 1) {
            // Port to start the webserver on
            Integer serverPort = Integer.parseInt(args[0]);
            System.out.println("main | Starting server on " + serverPort + " port...");
            routerConfig.put("port", serverPort);
        }

        // Commandline Argument: Response Delay
        if (args.length == 2) {
            String responseDelay = args[1];
            System.out.println("main | Response will be dealayed by " + responseDelay+"ms.");
            routerConfig.put("responseDelay", Integer.parseInt(responseDelay));
        }

        // Commandline Argument: SSL configuration?
        if (args.length == 3) {
            String isServerSsl = args[2];
            System.out.println("main | Is SSL configured for webserver? " + isServerSsl);
            boolean isSsl = isServerSsl.equals("true") ? true : false;
            routerConfig.put("isSsl", isSsl);
        }

        // Verticle Deployment Options
        DeploymentOptions routerDeploymentOptions = new DeploymentOptions().setInstances(1).setConfig(routerConfig);

        // Router verticle deployment handler
        vertx.deployVerticle(Routes.class, routerDeploymentOptions, handler -> {
            if (handler.succeeded()) {
                System.out.println("main | verticle deployment succeeded...");
            } else {
                System.out.println("main | verticle deployment failed: "+handler.cause());
            }
        });


    }
}
