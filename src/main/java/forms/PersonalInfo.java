package forms;

public class PersonalInfo {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;
    private String urlPhoto;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    PersonalInfo(PersonalInfoBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.urlPhoto = builder.urlPhoto;
    }

    //BuilderClass
    public static class PersonalInfoBuilder {
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String phoneNumber;
        private String urlPhoto;

        public PersonalInfoBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonalInfoBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonalInfoBuilder setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

//        public PersonalInfoBuilder setPhoneNumber(String phoneNumber) {
//            int ph = Integer.parseInt(phoneNumber);
//            if (ph >= 5 && ph <= 15) {
//                this.phoneNumber = phoneNumber;
//                return this;
//            } else {
//                return null;
//            }
//        }

        public PersonalInfoBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PersonalInfoBuilder setUrlPhoto(String urlPhoto) {
            this.urlPhoto = urlPhoto;
            return this;
        }

        public PersonalInfo build() {
            return new PersonalInfo(this);
        }
    }

}
