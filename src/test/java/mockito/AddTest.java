package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.BDDMockito.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AddTest {
    @InjectMocks
    private add add;
    @Mock
    private ValidNumber validNumber;
    @Mock
    private Print print;

    @Captor
    private ArgumentCaptor<Integer> captor;
    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void addTest(){
        when(validNumber.check(3)).thenReturn(false);
        boolean checkNumber = validNumber.check(3);
        assertEquals(false, checkNumber);


        when(validNumber.check("a")).thenReturn(false);
        checkNumber = validNumber.check(3);
        assertEquals(false, checkNumber);
    }

    @Test
    public void addRealMethodTest(){
        when(validNumber.check("3")).thenCallRealMethod();
        assertEquals(false,validNumber.check(3));
    }
    @Test
    public void addMockExceptionTest(){
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero"));
        Exception exception = null;
        try{
            validNumber.checkZero(0);
        }
        catch (ArithmeticException e){
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void addDoubleToIntThenAnswerTest(){
        Answer<Integer> answer = new Answer<Integer>(){
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 7;
            }

        };
        when(validNumber.doubleToInt(7.7)).thenAnswer(answer);
        assertEquals(14,add.addInt(7.7));
    }
    @Test
    public void patternTest(){
        //Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        //Act
        int result = add.add(4,5);
        //Assert
        assertEquals(9, result);

    }
    @Test
    public void patterngivenTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        int result = add.add(4,5);
        //Then
        assertEquals(9, result);

    }
    @Test
    public void argumentMatcherTest(){
        //Given
        given(validNumber.check(anyInt())).willReturn(true);
        given(validNumber.check(anyInt())).willReturn(true);
        //When
        int result = add.add(3,6);
        //Then
        assertEquals(9, result);

    }
    @Test
    public void addPrintTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(validNumber,never()).check(99);
        verify(validNumber,atLeast(1)).check(4);
        verify(validNumber,atMost(3)).check(4);

        verify(print).showMessage(9);
        verify(print,never()).showError();
    }

    @Test
    public void CaptorTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(print).showMessage(captor.capture());
        assertEquals(captor.getValue().intValue(),9);
    }

    @Test
    public void spyTest(){
        spyList.add("1");
        spyList.add("2");
        verify (spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2,spyList.size());


    }
@Test
    public void mockTest(){
        mockList.add("1");
        mockList.add("2");
        verify (mockList).add("1");
        verify(mockList).add("2");
        verify(mockList,never()).add("2");
        assertEquals(2,mockList.size());


    }

}