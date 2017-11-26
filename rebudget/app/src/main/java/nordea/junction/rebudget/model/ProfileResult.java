package nordea.junction.rebudget.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class ProfileResult {

    private int status;

    private String errors;

    public int getStatus() {
        return status;
    }

    public String getErrors() {
        return errors;
    }

    public ProfileResult() {
    }

    @SerializedName("data")
    private ProfileEntity profileEntity;

    private String version;

    public ProfileEntity getProfileEntity() {
        return profileEntity;
    }

    public String getVersion() {
        return version;
    }

    public class ProfileEntity {

        @SerializedName("id")
        private String id = "";

        @SerializedName("first_name")
        private String firstName = "";

        @SerializedName("last_name")
        private String lastName = "";

        @SerializedName("name")
        private String name = "";

        @SerializedName("account")
        private String account = "";

        @SerializedName("phone")
        private String phone = "";

        @SerializedName("phone_id")
        private String phoneId = "";

        @SerializedName("icon")
        private String icon = "";

        @SerializedName("logintoken")
        private String loginToken = "";

        @SerializedName("private_key")
        private String privateKey = "";

        @SerializedName("allow_locations")
        private String allowLocations = "";

        @SerializedName("updated")
        private String updated = "";

        @SerializedName("organization_id")
        private String organizationId = "";

        public ProfileEntity() {
        }

        public String getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getName() {
            return name;
        }

        public String getAccount() {
            return account;
        }

        public String getPhone() {
            return phone;
        }

        public String getPhoneId() {
            return phoneId;
        }

        public String getIcon() {
            return icon;
        }

        public @Nullable
        String getLoginToken() {
            return loginToken;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getAllowLocations() {
            return allowLocations;
        }

        public String getUpdated() {
            return updated;
        }

        public String getOrganizationId() {
            return organizationId;
        }
    }

}