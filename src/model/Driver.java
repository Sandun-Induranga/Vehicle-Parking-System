package model;

public class Driver {
    private String driverName;
    private String nic;
    private String licenseNum;
    private String address;
    private String contactNum;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public Driver(String driverName, String nic, String licenseNum, String address, String contactNum) {
        this.driverName = driverName;
        this.nic = nic;
        this.licenseNum = licenseNum;
        this.address = address;
        this.contactNum = contactNum;
    }

    @Override
    public String toString() {
        return "model.Driver{" +
                "driverName='" + driverName + '\'' +
                ", nic='" + nic + '\'' +
                ", licenseNum='" + licenseNum + '\'' +
                ", address='" + address + '\'' +
                ", contactNum='" + contactNum + '\'' +
                '}';
    }
}
