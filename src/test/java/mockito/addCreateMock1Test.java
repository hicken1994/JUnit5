package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class addCreateMock1Test {
 private add add;
 private ValidNumber validNumber;
@BeforeEach
    public void setUp(){
    validNumber = Mockito.mock(ValidNumber.class);
    add= new add(validNumber);
}
@Test
    public void addTest(){
    add.add(3,2);
    Mockito.verify(validNumber).check(3);

}
}
