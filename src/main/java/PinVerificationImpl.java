
/**
 * Created by Nadiia on 31.07.2016.
 */
public class PinVerificationImpl implements PinVerification {
    private final int[] expectedPIN;
    private int attempt = 0;
    private boolean isAccessedToAccount = false;

    public PinVerificationImpl(int[] expectedPIN){
        this.expectedPIN = expectedPIN;
    }

    public boolean enterToAccount(int [] testPin){
        if(isAccessedToAccount){
            System.out.print("Access to Account");
        }else {
            if(attempt<3) {
                System.out.println("Please, enter PIN");
                System.out.println("****");
                attempt++;
                System.out.print(attempt+ " attempt:");
                if(testPin==expectedPIN){
                    isAccessedToAccount = true;
                    System.out.print("Access to Account");
                }else if(attempt==3){
                    System.out.print("Wrong pin Your card has been eaten. Please contact support service.");
                }
            }else {
                System.out.print("Your card has been eaten. Please contact support service.");
            }
        }
        return isAccessedToAccount;
    }

}

