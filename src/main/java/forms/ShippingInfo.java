package forms;

public class ShippingInfo {
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String country;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    private ShippingInfo(ShippingInfoBuilder builder) {
        this.street = builder.street;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
        this.state = builder.state;
        this.country = builder.country;
    }

    //Builder Class
    public static class ShippingInfoBuilder {
        private String street;
        private String city;
        private String zipCode;
        private String state;
        private String country;

        public ShippingInfoBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public ShippingInfoBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public ShippingInfoBuilder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public ShippingInfoBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public ShippingInfoBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public ShippingInfo build() {
            return new ShippingInfo(this);
        }
    }
}