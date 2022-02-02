package com.freshworks.webserver;

import io.vertx.core.AbstractVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.UUID;

public class Routes extends AbstractVerticle {

    long responseDelay = 1000;
    int ipCount = 0;
    int nicCount = 0;
    int diskCount = 0;
    int vmStatusCount = 0;
    int vmCount = 0;
    static HashMap couterMap = new HashMap();
    JsonArray array = new JsonArray();

    @Override
    public void start() {

        if (config().containsKey("responseDelay")) {
            responseDelay = config().getInteger("responseDelay");
        }
        Router router = Router.router(getVertx());

        System.out.println("Routes | start | Routes are defined here..");
        oktaUsersList();

        /**
         * ******************************* Okta routes section
         * ****************************************************************
         */
        // Users listing
        router.route(HttpMethod.GET, "/api/v1/users/:userId").handler(requestHandler -> {
            System.out.println("Okta cred validate route hit...");
            sendJsonResponse(requestHandler, new JsonObject());
        });
        // Users listing
        router.route(HttpMethod.GET, "/api/v1/users").handler(requestHandler -> {
            System.out.println("Okta user list hit...");
            sendArrayResponse(requestHandler, STATIC.RESPONSE.getOktaUserResponse(), getVertx());
        });

        // Apps Listing
        router.route(HttpMethod.GET, "/api/v1/apps").handler(requestHandler -> {
            System.out.println("Okta app list hit...");
            sendArrayResponse(requestHandler, STATIC.RESPONSE.getOktaAppsListResponse(), getVertx());
        });

        // Get App Users
        router.route(HttpMethod.GET, "/api/v1/apps/:appId/users").handler(requestHandler -> {
            System.out.println("Okta users with access to app route hit...");
            System.out.println("App ID: " + requestHandler.request().getParam("appId"));
            sendArrayResponse(requestHandler, STATIC.RESPONSE.getOktaAppsUsersListResponse(), getVertx());
        });

        // Get Logs
        router.route(HttpMethod.GET, "/api/v1/logs").handler(requestHandler -> {
            System.out.println("Okta logs route hit...");
            JsonArray arrayofevents = new JsonArray();
            JsonArray assignedusers = STATIC.RESPONSE.getOktaAppsUsersListResponse();
            for (Object assigneduser : assignedusers) {
                JsonObject assuser = JsonObject.mapFrom(assigneduser);
                JsonArray j = STATIC.RESPONSE.getOktaLogsResponse();
                j.getJsonObject(0).getJsonObject("actor").put("id", assuser.getString("id"));
                arrayofevents.add(j.getJsonObject(0));
            }

            sendArrayResponse(requestHandler, arrayofevents, getVertx());
            System.out.println("Query params: " + requestHandler.request().query());
        });

        /**
         * ******************************* Zoom routes section
         * ****************************************************************
         */
        // Users list
        router.route(HttpMethod.GET, "/v2/users/me").handler(requestHandler -> {
            System.out.println("Zoom cred validate route hit...");
            sendJsonResponse(requestHandler, new JsonObject());

        });
        // Get Plan Usages
        router.route(HttpMethod.GET, "/v2/accounts/me/plans/usage").handler(requestHandler -> {
            System.out.println("Zoom plan usage route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listZoomPlanUsages());

        });

        // Users list
        router.route(HttpMethod.GET, "/v2/users").handler(requestHandler -> {
            System.out.println("Zoom users list route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listZoomUsers());

        });

        // Signin Signout Activities
        router.route(HttpMethod.GET, "/v2/report/activities").handler(requestHandler -> {
            System.out.println("Zoom signin signout activities list route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listZoomSigninSignoutActivities());

        });

        // Users meetings list
        router.route(HttpMethod.GET, "/v2/report/users/:userId/meetings").handler(requestHandler -> {
            System.out.println("Zoom user meetings list route hit...");
            System.out.println("User ID: " + requestHandler.request().getParam("userId"));

            JsonArray arrayofmeets = new JsonArray();
            JsonObject meets = STATIC.RESPONSE.listZoomUsersMeetings();
            for (Object meet : meets.getJsonArray("meetings")) {
                JsonObject assuser = JsonObject.mapFrom(meet);
                assuser.put("host_id", requestHandler.request().getParam("userId"));
                arrayofmeets.add(assuser);
            }
            meets.put("meetings", arrayofmeets);

            sendJsonResponse(requestHandler, meets);

        });

        /**
         * ******************************* GSuite routes section
         * ****************************************************************
         */
        // token authorization
        router.route(HttpMethod.POST, "/token").handler(requestHandler -> {
            System.out.println("GSuite cred validate route hit...");
            JsonObject respoJsonObject = new JsonObject();
            respoJsonObject.put("access_token", "testtoken");
            sendJsonResponse(requestHandler, respoJsonObject);
        });

        // Users list
        router.route(HttpMethod.GET, "/admin/directory/v1/users").handler(requestHandler -> {
            System.out.println("GSuite users list route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listGsuiteUsers());
        });

        // User Tokens list
        router.route(HttpMethod.GET, "/admin/directory/v1/users/:userKey/tokens").handler(requestHandler -> {
            System.out.println("GSuite user tokens list route hit...");
            System.out.println("User Key: " + requestHandler.request().getParam("userKey"));

            JsonObject tokens = STATIC.RESPONSE.listGsuiteUserTokens();
            JsonArray f = new JsonArray();
            for (Object token : tokens.getJsonArray("items")) {
                JsonObject tokenJson = JsonObject.mapFrom(token);
                tokenJson.put("userKey", requestHandler.request().getParam("userKey"));
                f.add(tokenJson);
            }
            tokens.put("items", f);
            sendJsonResponse(requestHandler, tokens);

        });

        // Usage reports
        router.route(HttpMethod.GET, "/admin/reports/v1/usage/dates/:date").handler(requestHandler -> {
            System.out.println("GSuite app usage reports route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listGsuiteUsageReports());
            System.out.println("Date: " + requestHandler.request().getParam("date"));
            System.out.println("Query params: " + requestHandler.request().query());
        });

        // All users usage reports
        router.route(HttpMethod.GET, "/admin/reports/v1/usage/users/all/dates/:date").handler(requestHandler -> {
            System.out.println("GSuite user usage reports route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listGsuiteUserUsageReports());
            System.out.println("Date: " + requestHandler.request().getParam("date"));
            System.out.println("Query params: " + requestHandler.request().query());
        });

        /**
         * ************************************** Slack routes section
         * ****************************************************************
         */
        router.route(HttpMethod.GET, "/api/auth.test").handler(requestHandler -> {
            System.out.println("Slack slackCheckCredentials route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.slackCheckCredentials());
//            System.out.println("Query params: " + requestHandler.request().query());
        });
        router.route(HttpMethod.GET, "/api/users.list").handler(requestHandler -> {
            System.out.println("Slack users list route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listSlackUsers());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/api/team.billableInfo").handler(requestHandler -> {
            System.out.println("Slack team bill info route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listSlackBillableInfo());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/api/team.accessLogs").handler(requestHandler -> {
            System.out.println("Slack team access logs route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.listSlackAccessLogs());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        /**
         * ************************************** Microsoft365 routes section
         * ****************************************************************
         */
        router.route(HttpMethod.GET, "/:tenantId/oauth2/v2.0/token").handler(requestHandler -> {
            System.out.println("Microsoft365 cred validate route hit...");
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("token_type", "Bearer");
            jsonObject.put("expires_in", 3599);
            jsonObject.put("ext_expires_in", 3599);
            jsonObject.put("access_token", "test token");
            sendJsonResponse(requestHandler, jsonObject);
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getOffice365ActivationsUserCounts").handler(requestHandler -> {
            System.out.println("Microsoft365 getOffice365ActivationsUserCounts route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getOffice365ActivationsUserCounts());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getTeamsUserActivityUserCounts(period='D90')").handler(requestHandler -> {
            System.out.println("Microsoft365 getTeamsUserActivityUserCounts route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getTeamsUserActivityUserCounts());
//            System.out.println("Query params: " + requestHandler.request().query());
        });
        router.route(HttpMethod.GET, "/v1.0/reports/getTeamsUserActivityUserCounts(period='D7')").handler(requestHandler -> {
            System.out.println("Microsoft365 getTeamsUserActivityUserCounts route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getTeamsUserActivityUserCounts());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getMailboxUsageStorage(period='D90')").handler(requestHandler -> {
            System.out.println("Microsoft365 getMailboxUsageStorage route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getMailboxUsageStorage());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getMailboxUsageStorage(period='D7')").handler(requestHandler -> {
            System.out.println("Microsoft365 getMailboxUsageStorage route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getMailboxUsageStorage());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getOneDriveUsageStorage(period='D90')").handler(requestHandler -> {
            System.out.println("Microsoft365 getOneDriveUsageStorage route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getOneDriveUsageStorage());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getOneDriveUsageStorage(period='D7')").handler(requestHandler -> {
            System.out.println("Microsoft365 getOneDriveUsageStorage route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getOneDriveUsageStorage());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getOffice365ActivationsUserDetail").handler(requestHandler -> {
            System.out.println("Microsoft365 getOffice365ActivationsUserDetail route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getOffice365ActivationsUserDetail());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getTeamsUserActivityUserDetail(period='D90')").handler(requestHandler -> {
            System.out.println("Microsoft365 getTeamsUserActivityUserDetail route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getTeamsUserActivityUserDetail());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getTeamsUserActivityUserDetail(period='D7')").handler(requestHandler -> {
            System.out.println("Microsoft365 getTeamsUserActivityUserDetail route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getTeamsUserActivityUserDetail());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getOneDriveActivityUserDetail(period='D90')").handler(requestHandler -> {
            System.out.println("Microsoft365 getOneDriveActivityUserDetail route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getOneDriveActivityUserDetail());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getOneDriveActivityUserDetail(period='D7')").handler(requestHandler -> {
            System.out.println("Microsoft365 getOneDriveActivityUserDetail route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getOneDriveActivityUserDetail());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getEmailActivityUserDetail(period='D90')").handler(requestHandler -> {
            System.out.println("Microsoft365 getEmailActivityUserDetail route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getEmailActivityUserDetail());
//            System.out.println("Query params: " + requestHandler.request().query());
        });

        router.route(HttpMethod.GET, "/v1.0/reports/getEmailActivityUserDetail(period='D7')").handler(requestHandler -> {
            System.out.println("Microsoft365 getEmailActivityUserDetail route hit...");
            sendCSVResponse(requestHandler, STATIC.RESPONSE.getEmailActivityUserDetail());
//            System.out.println("Query params: " + requestHandler.request().query());
        });
        /**
         * ******************************* Dropbox routes section
         * ****************************************************************
         */
        // Team info
        router.route(HttpMethod.POST, "/team/get_info").handler(requestHandler -> {
            System.out.println("Dropbox team info route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getTeamInfo());
        });

        // Users list
        router.route(HttpMethod.POST, "/team/members/list").handler(requestHandler -> {
            System.out.println("Dropbox users list route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getMembersList());
        });

        // Events
        router.route(HttpMethod.POST, "/team_log/get_events").handler(requestHandler -> {
            System.out.println("Dropbox login events route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getEvents());
        });

        /**
         * ************************************** AZURE AD routes section
         * ****************************************************************
         */
        // List Apps

        router.route(HttpMethod.GET, "/v1.0/applications").handler(requestHandler -> {
            System.out.println("Azuread list apps route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getAzureAdApps());
        });

        // List service principal of app
        router.route(HttpMethod.GET, "/beta/servicePrincipals").handler(requestHandler -> {
            System.out.println("Azuread list servicePrincipals route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getServicePrincipals());
        });

        // Get principal
        router.route(HttpMethod.GET, "/beta/servicePrincipals/:appId/appRoleAssignedTo").handler(requestHandler -> {
            System.out.println("Azuread Get principal route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getPrincipalId());
        });

        // List group members of service principal group
        router.route(HttpMethod.GET, "/v1.0/groups/:groupId/members").handler(requestHandler -> {
            System.out.println("Azuread list group members route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getUsersOfGroup());
        });

        // List of events of users
        router.route(HttpMethod.GET, "/beta/auditLogs/signIns").handler(requestHandler -> {
            System.out.println("Azuread list events route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getSignInEvents());
        });

        // List deleted users
        router.route(HttpMethod.GET, "/v1.0/auditLogs/directoryAudits").handler(requestHandler -> {
            System.out.println("Azuread list deleted users route hit...");
            sendJsonResponse(requestHandler, STATIC.RESPONSE.getRemovedUsers());
        });

        /**
         * Azure Cloud Discovery
         */
        // List PUBLIC_IP_ADDRESS
        router.route(HttpMethod.GET, "/subscriptions/:subscriptionId/providers/Microsoft.Network/publicIPAddresses").handler(requestHandler -> {
            System.out.println("Azure cloud PUBLIC_IP_ADDRESS route hit..."+requestHandler.queryParams());
            System.out.println("Azure cloud PUBLIC_IP_ADDRESS route hit..."+requestHandler.request().getHeader("Authorization"));
            try {
                if(!couterMap.containsKey(requestHandler.request().getHeader("Authorization"))){
                    couterMap.put(requestHandler.request().getHeader("Authorization"), 0);
                }
                JsonArray value = new JsonArray();
                JsonObject staticResponse = STATIC.RESPONSE.getAzureCloudListPublicIPAddressesStringResponse();
                for (int i = 1; i <= 100; i++) {
                    JsonObject rec = new JsonObject(staticResponse.getJsonArray("value").getValue(0).toString());
                    rec.put("name", rec.getString("name") + "-" + (Integer.parseInt(couterMap.get(requestHandler.request().getHeader("Authorization")).toString()) + 1));
                    rec.put("id", rec.getString("id") + "-" + (Integer.parseInt(couterMap.get(requestHandler.request().getHeader("Authorization")).toString()) + 1));
                    value.add(rec);
                    couterMap.put(requestHandler.request().getHeader("Authorization"),Integer.parseInt(couterMap.get(requestHandler.request().getHeader("Authorization")).toString()) + 1) ;
                }
                JsonObject finalResponse = new JsonObject();
                finalResponse.put("value", value);
                System.out.println("Azure cloud asset count " + ipCount);
                if (ipCount <= 900) {
                    finalResponse.put("nextLink", staticResponse.getString("nextLink"));
                } else {
                    ipCount = 0;
                }
                sendJsonResponse(requestHandler, finalResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // List NETWORK_INTERFACES
        router.route(HttpMethod.GET, "/subscriptions/:subscriptionId/providers/Microsoft.Network/networkInterfaces").handler(requestHandler -> {
            System.out.println("Azure cloud NETWORK_INTERFACES route hit...");
            try {
                JsonArray value = new JsonArray();
                JsonObject staticResponse = STATIC.RESPONSE.getAzureCloudListNetworkInterfacesStringResponse();
                for (int i = 1; i <= 100; i++) {
                    JsonObject rec = new JsonObject(staticResponse.getJsonArray("value").getValue(0).toString());
                    rec.put("name", rec.getString("name") + "-" + (nicCount + 1));
                    rec.put("id", rec.getString("id") + "-" + (nicCount + 1));
                    value.add(rec);
                    nicCount = nicCount + 1;
                }
                JsonObject finalResponse = new JsonObject();
                finalResponse.put("value", value);
                System.out.println("Azure cloud asset count " + nicCount);
                if (nicCount <= 900) {
                    finalResponse.put("nextLink", staticResponse.getString("nextLink"));
                } else {
                    nicCount = 0;
                }
                sendJsonResponse(requestHandler, finalResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // List DISKS
        router.route(HttpMethod.GET, "/subscriptions/:subscriptionId/providers/Microsoft.Compute/disks").handler(requestHandler -> {
            System.out.println("Azure cloud DISKS route hit...");
            try {
                JsonArray value = new JsonArray();
                JsonObject staticResponse = STATIC.RESPONSE.getAzureCloudListDisksStringResponse();
                for (int i = 1; i <= 100; i++) {
                    JsonObject rec = new JsonObject(staticResponse.getJsonArray("value").getValue(0).toString());
                    rec.put("name", rec.getString("name") + "-" + (diskCount + 1));
                    rec.put("id", rec.getString("id") + "-" + (diskCount + 1));
                    rec.put("managedBy", rec.getString("managedBy") + "-" + (diskCount + 1));
                    rec.getJsonObject("properties").put("uniqueId", "test-unique-id" + "-" + (diskCount + 1));
                    value.add(rec);
                    diskCount = diskCount + 1;
                }
                JsonObject finalResponse = new JsonObject();
                finalResponse.put("value", value);
                System.out.println("Azure cloud asset count " + diskCount);
                if (diskCount <= 900) {
                    finalResponse.put("nextLink", staticResponse.getString("nextLink"));
                } else {
                    diskCount = 0;
                }
                sendJsonResponse(requestHandler, finalResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // List VIRTUAL_MACHINE/VIRTUAL_MACHINE_STATUS
        router.route(HttpMethod.GET, "/subscriptions/:subscriptionId/providers/Microsoft.Compute/virtualMachines").handler(requestHandler -> {
            System.out.println("Azure cloud VIRTUAL_MACHINE/VIRTUAL_MACHINE_STATUS route hit..." + requestHandler.queryParams());
            try {
                JsonArray value = new JsonArray();
                JsonObject staticResponse;
                JsonObject finalResponse = new JsonObject();

                if (requestHandler.queryParams().contains("statusOnly")) {
                    staticResponse = STATIC.RESPONSE.getAzureCloudListVMsStringResponse(true);
                    for (int i = 1; i <= 100; i++) {
                        JsonObject rec = new JsonObject(staticResponse.getJsonArray("value").getValue(0).toString());
                        rec.put("name", rec.getString("name") + "-" + (vmStatusCount + 1));
                        rec.put("id", rec.getString("id") + "-" + (vmStatusCount + 1));
                        rec.getJsonObject("properties").put("vmId", "test-vmId" + "-" + (vmStatusCount + 1));
                        value.add(rec);
                        vmStatusCount = vmStatusCount + 1;
                    }
                    finalResponse.put("value", value);
                    System.out.println("Azure cloud asset count " + vmStatusCount);
                    if (vmStatusCount <= 900) {
                        finalResponse.put("nextLink", staticResponse.getString("nextLink"));
                    } else {
                        vmStatusCount = 0;
                    }
                    sendJsonResponse(requestHandler, finalResponse);
                } else {
                    staticResponse = STATIC.RESPONSE.getAzureCloudListVMsStringResponse(false);
                    for (int i = 1; i <= 100; i++) {
                        JsonObject rec = new JsonObject(staticResponse.getJsonArray("value").getValue(0).toString());
                        rec.put("name", rec.getString("name") + "-" + (vmCount + 1));
                        rec.put("id", rec.getString("id") + "-" + (vmCount + 1));
                        rec.getJsonObject("properties").put("vmId", "test-vmId" + "-" + (vmCount + 1));
                        rec.getJsonObject("properties").getJsonObject("storageProfile").getJsonArray("dataDisks").getJsonObject(0).getJsonObject("managedDisk")
                                .put("id", rec.getJsonObject("properties").getJsonObject("storageProfile").getJsonArray("dataDisks").getJsonObject(0).getJsonObject("managedDisk").getString("id") + "-" + (vmCount + 1));
                        rec.getJsonObject("properties").getJsonObject("storageProfile").getJsonArray("dataDisks").getJsonObject(0)
                                .put("name", rec.getJsonObject("properties").getJsonObject("storageProfile").getJsonArray("dataDisks").getJsonObject(0).getString("name") + "-" + (vmCount + 1));
                        rec.getJsonObject("properties").getJsonObject("networkProfile").getJsonArray("networkInterfaces").getJsonObject(0)
                                .put("id", rec.getJsonObject("properties").getJsonObject("networkProfile").getJsonArray("networkInterfaces").getJsonObject(0).getString("id") + "-" + (vmCount + 1));
                        value.add(rec);
                        vmCount = vmCount + 1;
                    }
                    finalResponse.put("value", value);
                    System.out.println("Azure cloud asset count " + vmCount);
                    if (vmCount <= 900) {
                        finalResponse.put("nextLink", staticResponse.getString("nextLink"));
                    } else {
                        vmCount = 0;
                    }
                    sendJsonResponse(requestHandler, finalResponse);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        /**
         * ************************************************** HTTP Server
         * Configurations ************************************************
         */
//        boolean isSsl = config().getBoolean("isSsl");
        Integer port = config().getInteger("port");
        if (port == null) {
            port = 8080;
        }
        System.out.println("Starting webserver on port " + port);

        // HTTP Server Initialization
        getVertx().createHttpServer().requestHandler(router).listen(port).onSuccess(successHandler -> {
            System.out.println("Routes | start | HTTP server started successfully...");
        }).onFailure(failureHandler -> {
            System.out.println("Routes | start | HTTP server failed to start. \nReason: " + failureHandler.getMessage());
        });

    }

    /**
     * **************************************************************** Common
     * Methods
     * **********************************************************************
     */
    /**
     * Sends JSON server response
     *
     * @param requestHandler
     * @param responseJson
     */
    void sendJsonResponse(RoutingContext requestHandler, JsonObject responseJson) {
        try {
            // Timer will send the response after the specified delay
            getVertx().setTimer(responseDelay, delayHandler -> {
                System.out.println("setTimer | Sending delayed response after " + responseDelay + "ms.");
                requestHandler.response()
                        .putHeader("Content-Type", "application/json")
                        .setStatusMessage("success")
                        .setStatusCode(200)
                        .end(responseJson.toBuffer());
            });
        } catch (Exception exception) {
            System.out.println("sendJsonResponse | Exception occurred");
            exception.printStackTrace();
        }
    }

    /**
     * Sends array server response
     *
     * @param requestHandler
     * @param responseArray
     * @param vertx
     */
    void sendArrayResponse(RoutingContext requestHandler, JsonArray responseArray, Vertx vertx) {
        try {
            System.out.println("sendArrayResponse | Sending response...");

            // Timer will send the response after the specified delay
            vertx.setTimer(responseDelay, delayHandler -> {
                System.out.println("setTimer | Sending delayed response after " + responseDelay + "ms.");
                System.out.println("setTimer | Final Response: " + responseArray);

                requestHandler.response()
                        .putHeader("Content-Type", "application/json")
                        .setStatusMessage("success")
                        .setStatusCode(200)
                        .send(responseArray.toBuffer());
            });

        } catch (Exception exception) {
            System.out.println("Exception in sending resposne");
            exception.printStackTrace();
        }
    }

    /**
     * Sends CSV reponse to server
     *
     * @param requestHandler
     * @param responseJson
     */
    void sendCSVResponse(RoutingContext requestHandler, String responseJson) {
        try {
            // Timer will send the response after the specified delay
            getVertx().setTimer(responseDelay, delayHandler -> {
                System.out.println("setTimer | Sending delayed response after " + responseDelay + "ms.");
                requestHandler.response()
                        .putHeader("Content-Type", "application/octet-stream")
                        .setStatusMessage("success")
                        .setStatusCode(200)
                        .end(responseJson);
            });
        } catch (Exception exception) {
            System.out.println("sendJsonResponse | Exception occurred");
            exception.printStackTrace();
        }
    }

    /**
     * *****************************************************************
     * Generate Data
     * ******************************************************************
     */
    void oktaUsersList() {
        System.out.println("oktaUsersList...");
        int totalCount = 1000;
        int userCount = 0;
        JsonArray finalResponse = new JsonArray();

        for (userCount = 0; userCount <= totalCount; userCount++) {
            UUID uuid = UUID.randomUUID();
            JsonObject currentUser = STATIC.OKTA_DATA.oktaUsersListSample;
            currentUser.put("id", uuid);
            finalResponse.add(currentUser);
        }

        System.out.println(finalResponse);

    }
}
