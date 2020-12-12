package com.activity.a17041271_tranhieunghia_apimockup;

public class User {
    private int id;
    private String FIRSTNAME;
    private String LASTNAME;
    private String GENDER;
    private String SALARY;

    public User(int id, String FIRSTNAME, String LASTNAME, String GENDER, String SALARY) {
        this.id = id;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.GENDER = GENDER;
        this.SALARY = SALARY;
    }

    public User() {
    }



    public int getId() {
        return id;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getSALARY() {
        return SALARY;
    }

    public void setSALARY(String SALARY) {
        this.SALARY = SALARY;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", FIRSTNAME='" + FIRSTNAME + '\'' +
                ", LASTNAME='" + LASTNAME + '\'' +
                ", GENDER='" + GENDER + '\'' +
                ", SALARY='" + SALARY + '\'' +
                '}';
    }
}
