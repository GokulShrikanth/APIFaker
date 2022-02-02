package com.freshworks.webserver;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class STATIC {

    public static final class ROUTES {

    }

    public static class RESPONSE {

        // Okta - Generate users list response
        static JsonArray getOktaUserResponse() {

            JsonArray response = new JsonArray();

            try {
                System.out.println("getOktaUserResponse | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/okta_users_list.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonArray(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getOktaResponse | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Okta - Generate apps list response
        static JsonArray getOktaAppsListResponse() {

            JsonArray response = new JsonArray();

            try {
                System.out.println("getOktaAppsListResponse | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/okta_apps_list.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonArray(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getOktaAppsListResponse | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Okta - Generate app users list response
        static JsonArray getOktaAppsUsersListResponse() {

            JsonArray response = new JsonArray();

            try {
                System.out.println("getOktaAppsListResponse | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/okta_app_users_list.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonArray(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getOktaAppsListResponse | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Okta - Get Logs
        static JsonArray getOktaLogsResponse() {

            JsonArray response = new JsonArray();

            try {
                System.out.println("getOktaLogsResponse | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/okta_logs.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonArray(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getOktaLogsResponse | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // GSuite - Users List Response
        static JsonObject listGsuiteUsers() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listGsuiteUsers | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/gsuite_users_list.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("listGsuiteUsers | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // GSuite - Users Tokens List Response
        static JsonObject listGsuiteUserTokens() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listGsuiteUserTokens | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/gsuite_user_tokens.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("listGsuiteUserTokens | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // GSuite - Usage reports
        static JsonObject listGsuiteUsageReports() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listGsuiteUsageReports | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/gsuite_usage_reports.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("listGsuiteUsageReports | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // GSuite - User usage reports
        static JsonObject listGsuiteUserUsageReports() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listGsuiteUserUsageReports | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/gsuite_user_usage_reports.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("listGsuiteUserUsageReports | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Zoom - Plan usages
        static JsonObject listZoomPlanUsages() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listZoomPlanUsages | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/zoom_plan_usage.txt"));
                String zoomStringResponse = new String(file, "UTF-8");
                response = new JsonObject(zoomStringResponse);

            } catch (Exception exception) {
                System.out.println("listZoomPlanUsages | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Zoom - List users
        static JsonObject listZoomUsers() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listZoomUsers | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/zoom_users_list.txt"));
                String zoomStringResponse = new String(file, "UTF-8");
                response = new JsonObject(zoomStringResponse);

            } catch (Exception exception) {
                System.out.println("listZoomUsers | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Zoom - List Signin Signout activities
        static JsonObject listZoomSigninSignoutActivities() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listZoomSigninSignoutActivities | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/zoom_signin_signout_activities.txt"));
                String zoomStringResponse = new String(file, "UTF-8");
                response = new JsonObject(zoomStringResponse);

            } catch (Exception exception) {
                System.out.println("listZoomSigninSignoutActivities | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Zoom - List Users meetings
        static JsonObject listZoomUsersMeetings() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listZoomUsersMeetings | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/zoom_users_meetings.txt"));
                String zoomStringResponse = new String(file, "UTF-8");
                response = new JsonObject(zoomStringResponse);

            } catch (Exception exception) {
                System.out.println("listZoomUsersMeetings | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Slack - Users list
        static JsonObject listSlackUsers() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listSlackUsers | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/slack_users_list.txt"));
                String slackStringResponse = new String(file, "UTF-8");
                response = new JsonObject(slackStringResponse);

            } catch (Exception exception) {
                System.out.println("listSlackUsers | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        // Slack - Users list
        static JsonObject slackCheckCredentials() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listSlackUsers | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/slack_validate_credentials.txt"));
                String slackStringResponse = new String(file, "UTF-8");
                response = new JsonObject(slackStringResponse);

            } catch (Exception exception) {
                System.out.println("listSlackUsers | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Slack - Billabel info
        static JsonObject listSlackBillableInfo() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listSlackBillableInfo | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/slack_billable_info.txt"));
                String slackStringResponse = new String(file, "UTF-8");
                response = new JsonObject(slackStringResponse);

            } catch (Exception exception) {
                System.out.println("listSlackBillableInfo | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Slack - Billabel info
        static JsonObject listSlackAccessLogs() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listSlackAccessLogs | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/slack_access_logs.txt"));
                String slackStringResponse = new String(file, "UTF-8");
                response = new JsonObject(slackStringResponse);

            } catch (Exception exception) {
                System.out.println("listSlackAccessLogs | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        // Microsoft 365  - getEmailActivityUserDetail info
        static String getEmailActivityUserDetail() {
            String slackStringResponse = null;
            try {
                System.out.println("o365 | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/EmailActivityUserDetail.csv"));
                slackStringResponse = new String(file, "UTF-8");

            } catch (Exception exception) {
                System.out.println("o365 | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return slackStringResponse;
        }

        // Microsoft 365  - getTeamsUserActivityUserDetail info
        static String getTeamsUserActivityUserDetail() {
            String slackStringResponse = null;
            try {
                System.out.println("o365 | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/TeamsUserActivityUserDetail.csv"));
                slackStringResponse = new String(file, "UTF-8");

            } catch (Exception exception) {
                System.out.println("o365 | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return slackStringResponse;
        }

        // Microsoft 365  - getOffice365ActivationsUserDetail info
        static String getOffice365ActivationsUserDetail() {
            String slackStringResponse = null;
            try {
                System.out.println("o365 | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/Office365ActivationsUserDetail.csv"));
                slackStringResponse = new String(file, "UTF-8");

            } catch (Exception exception) {
                System.out.println("o365 | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return slackStringResponse;
        }

        // Microsoft 365  - getOneDriveActivityUserDetail info
        static String getOneDriveActivityUserDetail() {
            String slackStringResponse = null;
            try {
                System.out.println("o365 | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/OneDriveActivityUserDetail.csv"));
                slackStringResponse = new String(file, "UTF-8");

            } catch (Exception exception) {
                System.out.println("o365 | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return slackStringResponse;
        }

        // Microsoft 365  - getOffice365ActivationsUserCounts info
        static String getOffice365ActivationsUserCounts() {
            String slackStringResponse = null;
            try {
                System.out.println("o365 | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/Office365ActivationsUserCounts.csv"));
                slackStringResponse = new String(file, "UTF-8");

            } catch (Exception exception) {
                System.out.println("o365 | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return slackStringResponse;
        }

        // Microsoft 365  - getTeamsUserActivityUserCounts info
        static String getTeamsUserActivityUserCounts() {
            String slackStringResponse = null;
            try {
                System.out.println("o365 | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/TeamsUserActivityUserCounts.csv"));
                slackStringResponse = new String(file, "UTF-8");

            } catch (Exception exception) {
                System.out.println("o365 | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return slackStringResponse;
        }

        // Microsoft 365  - getMailboxUsageStorage info
        static String getMailboxUsageStorage() {
            String slackStringResponse = null;
            try {
                System.out.println("o365 | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/MailboxUsageStorage.csv"));
                slackStringResponse = new String(file, "UTF-8");

            } catch (Exception exception) {
                System.out.println("o365 | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return slackStringResponse;
        }

        // Microsoft 365  - getOneDriveUsageStorage info
        static String getOneDriveUsageStorage() {
            String slackStringResponse = null;
            try {
                System.out.println("o365 | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/OneDriveUsageStorage.csv"));
                slackStringResponse = new String(file, "UTF-8");

            } catch (Exception exception) {
                System.out.println("o365 | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return slackStringResponse;
        }
        
        // Dropbox - Team info Response
        static JsonObject getTeamInfo() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listGsuiteUsers | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/dropbox_team_info.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("listGsuiteUsers | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        
        // Dropbox - Users List Response
        static JsonObject getMembersList() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listGsuiteUsers | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/dropbox_members_list.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("listGsuiteUsers | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        
        // Dropbox - Login events Response
        static JsonObject getEvents() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("listGsuiteUsers | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/dropbox_events.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("listGsuiteUsers | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        
        // Azuread - List apps Response
        static JsonObject getAzureAdApps() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getAzureAdApps | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azuread_list_app.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getAzureAdApps | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        
        // Azuread - List service principal Response
        static JsonObject getServicePrincipals() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getServicePrincipals | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azuread_serviceprincipal.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getServicePrincipals | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        
        // Azuread - get principal id Response
        static JsonObject getPrincipalId() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getPrincipalId | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azuread_get_principalid.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getPrincipalId | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        
        // Azuread - Users of group Response
        static JsonObject getUsersOfGroup() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getUsersOfGroup | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azuread_user_from_group.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getUsersOfGroup | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        
        // Azuread - Login events Response
        static JsonObject getSignInEvents() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getSignInEvents | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azuread_singins.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getSignInEvents | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
        
        // Azuread - Removed users Response
        static JsonObject getRemovedUsers() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getRemovedUsers | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azuread_removed_users.txt"));
                String oktaStringResponse = new String(file, "UTF-8");
                response = new JsonObject(oktaStringResponse);

            } catch (Exception exception) {
                System.out.println("getRemovedUsers | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        static JsonObject getAzureCloudListPublicIPAddressesStringResponse() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getAzureCloudListPublicIPAddressesStringResponse | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azurecloud_public_ip_addresses.txt"));
                String azureCloudListPublicIPAddressesStringResponse = new String(file, "UTF-8");
                response = new JsonObject(azureCloudListPublicIPAddressesStringResponse);

            } catch (Exception exception) {
                System.out.println("getAzureCloudListPublicIPAddressesStringResponse | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        static JsonObject getAzureCloudListNetworkInterfacesStringResponse() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getAzureCloudListNetworkInterfacesStringResponse | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azurecloud_network_interfaces.txt"));
                String azureCloudListPublicIPAddressesStringResponse = new String(file, "UTF-8");
                response = new JsonObject(azureCloudListPublicIPAddressesStringResponse);

            } catch (Exception exception) {
                System.out.println("getAzureCloudListNetworkInterfacesStringResponse | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        static JsonObject getAzureCloudListDisksStringResponse() {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getAzureCloudListDisksStringResponse | Current Directory: " + System.getProperty("user.dir"));

                byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azurecloud_disks.txt"));
                String azureCloudListPublicIPAddressesStringResponse = new String(file, "UTF-8");
                response = new JsonObject(azureCloudListPublicIPAddressesStringResponse);

            } catch (Exception exception) {
                System.out.println("getAzureCloudListDisksStringResponse | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }

        static JsonObject getAzureCloudListVMsStringResponse(Boolean statusOnly) {
            JsonObject response = new JsonObject();

            try {
                System.out.println("getAzureCloudListVMsStringResponse | Current Directory: " + System.getProperty("user.dir"));

                byte[] file;
                if(statusOnly){
                    file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azurecloud_vm_status.txt"));
                } else {
                    file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/azurecloud_vms.txt"));
                }
                String azureCloudListPublicIPAddressesStringResponse = new String(file, "UTF-8");
                response = new JsonObject(azureCloudListPublicIPAddressesStringResponse);

            } catch (Exception exception) {
                System.out.println("getAzureCloudListVMsStringResponse | Exception: " + exception.getMessage());
                exception.printStackTrace();
            }

            return response;
        }
    }

    public static class OKTA_DATA {

        public static JsonObject oktaUsersListSample = new JsonObject("{\"id\":\"00u1mm3cnsTQwAZxa4x7\",\"status\":\"ACTIVE\",\"created\":\"2020-12-22T08:30:11.000Z\",\"activated\":\"2020-12-22T08:30:11.000Z\",\"statusChanged\":\"2020-12-22T09:21:42.000Z\",\"lastLogin\":\"2020-12-23T11:05:07.000Z\",\"lastUpdated\":\"2020-12-22T09:21:42.000Z\",\"passwordChanged\":\"2020-12-22T09:21:42.000Z\",\"type\":{\"id\":\"otydw4gglA8ggWjX14x6\"},\"profile\":{\"firstName\":\"starforce198\",\"lastName\":\"starforce198\",\"mobilePhone\":null,\"secondEmail\":null,\"login\":\"volume-group.ffwfs85e@mailosaur.io\",\"email\":\"volume-group.ffwfs85e@mailosaur.io\"},\"credentials\":{\"password\":{},\"emails\":[{\"value\":\"volume-group.ffwfs85e@mailosaur.io\",\"status\":\"VERIFIED\",\"type\":\"PRIMARY\"}],\"recovery_question\":{\"question\":\"What is the food you least liked as a child?\"},\"provider\":{\"type\":\"OKTA\",\"name\":\"OKTA\"}},\"_links\":{\"self\":{\"href\":\"https://dev-597145.okta.com/api/v1/users/00u1mm3cnsTQwAZxa4x7\"}}}");
        public static JsonObject oktaEventsSample = new JsonObject("{\"actor\":{\"id\":\"00udw4gj9AfcLe2eB4x6\",\"type\":\"User\",\"alternateId\":\"snfsaas@gmail.com\",\"displayName\":\"SNF Saas\",\"detailEntry\":null},\"client\":{\"userAgent\":{\"rawUserAgent\":\"Okta-Integrations\",\"os\":\"Unknown\",\"browser\":\"UNKNOWN\"},\"zone\":\"null\",\"device\":\"Unknown\",\"id\":\"okta.b58d5b75-07d4-5f25-bf59-368a1261a405\",\"ipAddress\":\"18.220.28.44\",\"geographicalContext\":{\"city\":null,\"state\":null,\"country\":\"United States\",\"postalCode\":null,\"geolocation\":{\"lat\":37.751,\"lon\":-97.822}}},\"authenticationContext\":{\"authenticationProvider\":null,\"credentialProvider\":null,\"credentialType\":null,\"issuer\":null,\"interface\":null,\"authenticationStep\":0,\"externalSessionId\":\"unknown\"},\"displayMessage\":\"User single sign on to app\",\"eventType\":\"user.authentication.sso\",\"outcome\":{\"result\":\"SUCCESS\",\"reason\":null},\"published\":\"2021-01-26T19:09:43.425Z\",\"securityContext\":{\"asNumber\":null,\"asOrg\":null,\"isp\":null,\"domain\":null,\"isProxy\":null},\"severity\":\"INFO\",\"debugContext\":{\"debugData\":{\"initiationType\":\"NA\",\"redirectUri\":\"https://dev-597145-admin.okta.com/admin/sso/callback\",\"requestId\":\"YBBo92o0J4qvwgOphUIlFgAAFYA\",\"signOnMode\":\"OpenID Connect\",\"requestUri\":\"/oauth2/v1/token\",\"threatSuspected\":\"false\",\"url\":\"/oauth2/v1/token?\"}},\"legacyEventType\":\"app.auth.sso\",\"transaction\":{\"type\":\"WEB\",\"id\":\"YBBo92o0J4qvwgOphUIlFgAAFYA\",\"detail\":{}},\"uuid\":\"0c1f4f57-600a-11eb-b732-5d69bd3a866f\",\"version\":\"0\",\"request\":{\"ipChain\":[{\"ip\":\"18.220.28.44\",\"geographicalContext\":{\"city\":null,\"state\":null,\"country\":\"United States\",\"postalCode\":null,\"geolocation\":{\"lat\":37.751,\"lon\":-97.822}},\"version\":\"V4\",\"source\":null}]},\"target\":[{\"id\":\"0oadw2mtxXg78DZHc4x6\",\"type\":\"AppInstance\",\"alternateId\":\"Okta Admin Console\",\"displayName\":\"Okta Admin Console\",\"detailEntry\":{\"signOnModeType\":\"OPENID_CONNECT\"}},{\"id\":\"0uadw4gjdfV7cLaPw4x6\",\"type\":\"AppUser\",\"alternateId\":\"unknown\",\"displayName\":\"SNF Saas\",\"detailEntry\":null}]}");

    }

}
