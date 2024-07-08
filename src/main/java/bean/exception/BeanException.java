package bean.exception;

public class BeanException extends Exception{
    public BeanException(String msg){
        super(msg);
    }

    public BeanException(String instantiationOfBeanFailed, Exception e) {
        super(instantiationOfBeanFailed,e);
    }
}
