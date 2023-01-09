package forms;

public class CardInfo {
    private String nameOnCard;
    private String cardNumber;
    private String expirationDate;
    private String cvvValue;

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvvValue() {
        return cvvValue;
    }

    private CardInfo(CardInfoBuilder builder) {
        this.nameOnCard = builder.nameOnCard;
        this.cardNumber = builder.cardNumber;
        this.expirationDate = builder.expirationDate;
        this.cvvValue = builder.cvvValue;
    }

    public static class CardInfoBuilder {
        private String nameOnCard;
        private String cardNumber;
        private String expirationDate;
        private String cvvValue;

        public CardInfoBuilder setNameOnCard(String nameOnCard) {
            this.nameOnCard = nameOnCard;
            return this;
        }

        public CardInfoBuilder setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public CardInfoBuilder setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public CardInfoBuilder setCvvValue(String cvvValue) {
            this.cvvValue = cvvValue;
            return this;
        }

        public CardInfo build() {
            return new CardInfo(this);
        }
    }
}