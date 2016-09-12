package group5.com.finalsmad;

/**
 * Created by Pawan on 5/9/2016.
 */
public class Addresss {
    String countryCode,countryName,featureName,locale,maxAddressLineIndex;
    Double latitude,longtitude;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getMaxAddressLineIndex() {
        return maxAddressLineIndex;
    }

    public void setMaxAddressLineIndex(String maxAddressLineIndex) {
        this.maxAddressLineIndex = maxAddressLineIndex;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Addresss(String countryCode, String countryName, String featureName, String locale, String maxAddressLineIndex, Double latitude, Double longtitude) {

        this.countryCode = countryCode;
        this.countryName = countryName;
        this.featureName = featureName;
        this.locale = locale;
        this.maxAddressLineIndex = maxAddressLineIndex;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public Addresss() {

    }
}
