import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {
    private static Rational rGoodValue;
    private static Rational rBadValue;
    private static Rational rGoodValue1;
    private static Rational rGoodValue2;
    private static final String defaultValue = "0/1";

    @BeforeClass
    public static void init(){
        rGoodValue = new Rational(11,2);
        rBadValue = new Rational(2,11);
        rGoodValue1 = new Rational(11,2);
        rGoodValue2 = new Rational(11,2);
    }

    @Test
    public void equalsSymmetricTest() {
        assertEquals("Objects are not symmetry", rGoodValue.equals(rGoodValue1) , rGoodValue1.equals(rGoodValue));
    }

    @Test
    public void equalsReflexivityTest() {
        assertTrue("Objects are not reflexivity", rGoodValue.equals(rGoodValue));
    }

    @Test
    public void equalsTransitiveTest() {
        assertTrue("Objects are not transitive", rGoodValue.equals(rGoodValue1)
                == rGoodValue1.equals(rGoodValue2)
                == rGoodValue.equals(rGoodValue2));
    }

    @Test
    public void equalsUnequalObjectsTest() {
        assertFalse("Unequal objects ate equal",rGoodValue.equals(rBadValue));
    }

    @Test
    public void equalsUnequalClassObjectsTest() {
        assertFalse("Unequal objects (by class) are equal",rGoodValue.equals(new StringBuffer("stringbuffer")));
    }

    @Test
    public void equalsNullTest(){
        assertFalse("Test object is not null but equals null",rGoodValue1.equals(null));
    }

    @Test
    public void standardConstructorTest() {
        Rational standard = new Rational();
        assertEquals("Standard constructor returns wrong numerator", 0, standard.getNumerator());
        assertEquals("Standard constructor returns wrong denominator", 1, standard.getDenominator());
    }

    @Test
    public void argumentConstructorTest(){
        assertEquals("Constructor with arguments (int,int) returns wrong numerator",11,rGoodValue.getNumerator());
        assertEquals("Constructor with arguments (int,int) returns wrong denominator",2,rGoodValue.getDenominator());
    }

    @Test(expected = ArithmeticException.class)
    public void zeroDenominatorConstructorTest() {
        new Rational(1, 0);
    }

    @Test
    public void checkDefaultValue(){
        Rational rational = new Rational();
        assertEquals("Default value is wrong (not equals \"0/1\")",defaultValue,rational.toString());
    }
    @Test
    public void setterTest(){
        Rational rational = new Rational();
        rational.setNumerator(11);
        rational.setDenominator(2);
        assertEquals("setNumerator set wrong value",11,rational.getNumerator());
        assertEquals("setDenominator set wrong value",2,rational.getDenominator());
    }

    @Test
    public void reduceTest(){
        Rational rational = new Rational(200,5);
        assertEquals("reducing after setter returns wrong numerator",40,rational.getNumerator());
        assertEquals("reducing after setter returns wrong denominator",1,rational.getDenominator());
    }

    @Test
    public void reduceIrrationalTest(){
        Rational rational = new Rational(3,33);
        assertEquals("reducing after setter returns wrong numerator",1,rational.getNumerator());
        assertEquals("reducing after setter returns wrong denominator",11,rational.getDenominator());
    }

    @Test
    public void reduceNegativeNumeratorTest(){
        Rational rational = new Rational(-40,30);
        String expectedString = "-4/3";
        assertEquals("rational has incorrect value",expectedString,rational.toString());
    }

    @Test
    public void reduceNegativeDenominatorTest(){
        Rational rational = new Rational(40,-30);
        String expectedString = "-4/3";
        assertEquals("rational has incorrect value",expectedString,rational.toString());
    }

    @Test
    public void reduceNegativeAllArgsTest(){
        Rational rational = new Rational(-40,-30);
        final String expectedString = "4/3";
        assertEquals("rational has incorrect value",expectedString,rational.toString());
    }

    @Test
    public void isLessTest(){
        assertTrue("Less returns bad value",rBadValue.less(rGoodValue));
    }

    @Test
    public void isMoreNotLessTest(){
        assertFalse("Less returns bad value",rGoodValue.less(rBadValue));
    }

    @Test
    public void isLessWithZeroTest(){
        Rational firstRational = new Rational(5,32);
        Rational secondRational = new Rational(0,2);
        assertTrue("Less returns bad value",secondRational.less(firstRational));
    }

    @Test
    public void isMoreWithZeroTest(){
        Rational firstRational = new Rational(0,32);
        Rational secondRational = new Rational(2,2);
        assertFalse("Less returns bad value",secondRational.less(firstRational));
    }

    @Test
    public void isEqualNotLessTest(){
        assertFalse("Less returns bad value ",rBadValue.less(rBadValue));
    }

    @Test
    public void isEqualLessTest(){
        assertTrue("Less returns bad value ",rBadValue.lessOrEqual(rBadValue));
    }

    @Test
    public void additionRationalTest(){
        Rational firstRational = new Rational(32, 3);
        Rational secondRational = new Rational(4, 5);
        Rational expectedSum = new Rational(172, 15);

        assertEquals("Addition of two Rationals is wrong",
                expectedSum,firstRational.plus(secondRational));
    }

    @Test
    public void additionWithZeroRationalTest(){
        Rational firstRational = new Rational(0, 1);
        Rational secondRational = new Rational(4, 5);
        Rational expectedSum = new Rational(4, 5);

        assertEquals("Addition of two Rationals is wrong",
                expectedSum,firstRational.plus(secondRational));
    }

    @Test
    public void testMultiplicationRationalTest(){
        Rational firstRational = new Rational(9, 4);
        Rational secondRational = new Rational(4, 5);
        Rational expectedSum = new Rational(9, 5);

        assertEquals("Multiplication of two Rationals is wrong",
                expectedSum,firstRational.multiply(secondRational));
    }

    @Test
    public void testMultiplicationIrrationalTest(){
        Rational firstRational = new Rational(-9, 4);
        Rational secondRational = new Rational(4, 5);
        Rational expectedSum = new Rational(-9, 5);

        assertEquals("Multiplication of two Rationals is wrong",
                expectedSum,firstRational.multiply(secondRational));
    }

    @Test
    public void testMultiplicationBothIrrationalTest(){
        Rational firstRational = new Rational(-9, 4);
        Rational secondRational = new Rational(-4, 5);
        Rational expectedSum = new Rational(9, 5);

        assertEquals("Multiplication of two Rationals is wrong",
                expectedSum,firstRational.multiply(secondRational));
    }


    @Test
    public void subtractionRationalTest(){
        Rational firstRational = new Rational(9, 4);
        Rational secondRational = new Rational(4, 5);
        Rational expectedSum = new Rational(29, 20);

        assertEquals("Subtraction of two Rationals is wrong",
                expectedSum,firstRational.minus(secondRational));
    }

    @Test
    public void divisionRationalTest(){
        Rational firstRational = new Rational(72, 30);
        Rational secondRational = new Rational(24, 15);
        Rational expectedSum = new Rational(3, 2);
        assertEquals("Subtraction of two Rationals is wrong",
                expectedSum,firstRational.divide(secondRational));
    }

    @Test
    public void divisionIrrationalTest(){
        Rational firstRational = new Rational(-72, 30);
        Rational secondRational = new Rational(24, 15);
        Rational expectedSum = new Rational(-3, 2);
        assertEquals("Subtraction of two Rationals is wrong",
                expectedSum,firstRational.divide(secondRational));
    }

    @Test
    public void divisionBothIrrationalTest(){
        Rational firstRational = new Rational(-72, 30);
        Rational secondRational = new Rational(-24, 15);
        Rational expectedSum = new Rational(3, 2);
        assertEquals("Subtraction of two Rationals is wrong",
                expectedSum,firstRational.divide(secondRational));
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZeroTest(){
        Rational firstRational = new Rational(10,3);
        Rational secondRational = new Rational(0,34);
        firstRational.divide(secondRational);
        fail("divideByZeroTest Failed no throws arithmeticException when division by zero.");
    }
}