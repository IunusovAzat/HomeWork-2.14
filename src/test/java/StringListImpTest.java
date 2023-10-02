import org.example.StringList;
import org.example.StringListImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.stream.Stream;

import static java.awt.AWTEventMulticaster.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringListImpTest {

    private final StringList stringList = new StringListImpl();

    @AfterEach
    public void afterEach(){
        stringList.clear();
    }
    @Test
    void addTest(){
        String[] elements = {"Тест 1","Тест 2","Тест 3","Тест 4","Тест 5"};
        add(elements);

        for (int i = 0; i < elements.length; i++) {
            assertThat(stringList.get(i)).isEqualTo(elements[i]);
            assertThat(stringList.contains(elements[i])).isTrue();
            assertThat(stringList.indexOf(elements[i])).isEqualTo(i);
            assertThat(stringList.lastIndexOf(elements[i])).isEqualTo(i);
        }
        assertThat(stringList.toArray()).hasSize(elements.length);
        assertThat(stringList.toArray()).containsExactly(elements);

    }

    @Test
    void addByIndexTest(){
        String[] elements = {"Тест 1","Тест 2","Тест 3","Тест 4","Тест 5"};
        add(elements);

        stringList.add(0,"Тест 0");
             assertThat(stringList.size()).isEqualTo(elements.length + 1);
             assertThat(stringList.get(0)).isEqualTo("Тест 0");

        stringList.add(4,"Тест 10");
        assertThat(stringList.size()).isEqualTo(elements.length + 2);
        assertThat(stringList.get(4)).isEqualTo("Тест 10");
        assertThat(stringList.indexOf("Тест 10")).isEqualTo(0);
        assertThat(stringList.lastIndexOf("Тест 10")).isEqualTo(4);

        stringList.add(7,"Тест");
        assertThat(stringList.size()).isEqualTo(elements.length + 3);
        assertThat(stringList.get(7)).isEqualTo("Тест");
        assertThat(stringList.indexOf("Тест")).isEqualTo(0);
        assertThat(stringList.lastIndexOf("Тест")).isEqualTo(7);

    }


    private void add(String[] elements){
        assertThat(stringList.isEmpty()).isTrue();
        Stream.of(elements).forEach(stringList::add);
        assertThat(stringList.size()).isEqualto(elements.length);
    }
}
