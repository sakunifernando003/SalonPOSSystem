package org.example.demo.bo;


import org.example.demo.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=
                new BOFactory():boFactory;

    }
    public enum BOTypes{
        Employee, User, CUSTOMERPROFILE, APPOINTMENT, SERVICE, CUSTOMER
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case Employee:
                return new EmployeeBOImpl();
            case User:
                return new UserBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case CUSTOMERPROFILE:
                return new CustomerBOImpl();
            case SERVICE:
                return new ServiceBOImpl();


            default:
                return null;
        }
    }
}
