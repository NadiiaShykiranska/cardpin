import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Nadiia on 01.08.2016.
 */
public class PinTest {
    private static int [] expectedPIN, wrongPIN;
    private PinVerification pinVerification;

    @BeforeClass
    public static void initVariables(){
        expectedPIN = new int[]{1, 2, 3, 4};
        wrongPIN = new int[]{1, 2, 3};
    }

    @Before
    public void preconditions() {
        pinVerification = new PinVerificationImpl(expectedPIN);
    }


    @Test
    public void testFirstSuccessTry(){
        assertThat(pinVerification.enterToAccount(expectedPIN)).isEqualTo(true).describedAs("1 Try - PIN OK");
    }

    @Test
    public void testSecondSuccessTry(){
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("1 Try - PIN not OK");
        assertThat(pinVerification.enterToAccount(expectedPIN)).isEqualTo(true).describedAs("2 Try - PIN OK");
    }

    @Test
    public void testThirdSuccessTry(){
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("1 Try - PIN not OK");
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("2 Try - PIN not OK");
        assertThat(pinVerification.enterToAccount(expectedPIN)).isEqualTo(true).describedAs("3 Try - PIN OK");
    }

    @Test
    public void testCardEating(){
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("1 Try - PIN not OK");
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("2 Try - PIN not OK");
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("3 Try - PIN not OK");
    }

    @Test
    public void testTryToEnterExpectedPINAfterCardHasBeenEaten(){
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("1 Try - PIN not OK");
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("2 Try - PIN not OK");
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(false).describedAs("3 Try - PIN not OK");
        assertThat(pinVerification.enterToAccount(expectedPIN)).isEqualTo(false).describedAs("Card has been eaten after in previous step");
    }

    @Test
    public void testTryToEnterWrongPINAfterAccessToAccount(){
        assertThat(pinVerification.enterToAccount(expectedPIN)).isEqualTo(true).describedAs("1 Try - PIN OK");
        assertThat(pinVerification.enterToAccount(wrongPIN)).isEqualTo(true).describedAs("Access to Account in previous step");
    }
}
