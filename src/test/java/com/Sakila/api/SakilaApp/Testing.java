package com.Sakila.api.SakilaApp;

import com.google.common.collect.Iterables;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseBody;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Testing {

    private SakilaAppApplication sakilaAppApplication;

    @Mock
    FilmRepository filmRepository;
    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setup(){
        sakilaAppApplication = new SakilaAppApplication(filmRepository,categoryRepository);
    }
    @Test
    void TestAllFilms(){
        List<Film> filmList = new ArrayList<>();
        Film testFilm = new Film(10,"WOW","POG",1,20.06,32,12.45,"wo");
        Film testFilm2 = new Film(11,"WOW","POG",1,20.06,32,12.45,"wo");
        filmList.add(testFilm);
        filmList.add(testFilm2);
        Iterable<Film> filmIterable = filmList;
        when(filmRepository.findAll()).thenReturn(filmIterable);
        Iterable<Film> Expected = filmIterable;
        Iterable<Film> Actual = sakilaAppApplication.getAllFilms();
        Assertions.assertEquals(Expected, Actual, "Error");
    }
    @Test
    void TestGenerate6Film(){
        List<Film> filmList = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            filmList.add(new Film());
        }
        Iterable<Film> filmIterable = filmList;
        when(filmRepository.getRandom6Films()).thenReturn(filmIterable);
        Iterable<Film> Expected = filmIterable;
        int ExpectedSize = Iterables.size(filmIterable);
        Iterable<Film> Actual = sakilaAppApplication.getGenerateRandom();
        int ActualSize = Iterables.size(sakilaAppApplication.getGenerateRandom());
        Assertions.assertEquals(Expected,Actual,"Error");
        Assertions.assertEquals(ExpectedSize,ActualSize,"Does not have 6 films");
    }
    @Test
    void DeleteFilmById(){
        Film testFilm = new Film(10,"WOW","POG",1,20.06,32,12.45,"wo");
        sakilaAppApplication.addFilm(testFilm);
        sakilaAppApplication.deleteFilm(10);
        verify(filmRepository).deleteById(testFilm.getFilm_id());
        //Test to see if the function is being called. Does not test the data to see if it is actually deleted.
    }
    @Test
    void TestGettersFilmID(){
        Film testFilm = new Film(103,"WOW","POG",1,20.06,32,12.45,"wo");
        Assertions.assertEquals(103,testFilm.getFilm_id(),"Test 'getFilm_id' failed");
        Assertions.assertEquals("WOW",testFilm.getTitle(),"Test 'getTitle' failed");
        Assertions.assertEquals("POG",testFilm.getDescription(),"Test 'getDescription' failed");
        Assertions.assertEquals(1,testFilm.getRental_duration(),"Test 'getRental_duration' failed");
        Assertions.assertEquals(20.06,testFilm.getRental_rate(),"Test 'getRental_rate' failed");
        Assertions.assertEquals(32,testFilm.getLength(),"Test 'getLength' failed");
        Assertions.assertEquals(12.45,testFilm.getReplacement_cost(),"Test 'getReplacement_cost' failed");
        Assertions.assertEquals("wo",testFilm.getRating(),"Test 'getRating' failed");
    }
    @Test
    void TestSetterFilmID(){
        Film testFilm = new Film();
        testFilm.setFilm_id(1);
        testFilm.setTitle("Smile");
        testFilm.setDescription("I am a Joke");
        testFilm.setRental_duration(9);
        testFilm.setRental_rate(10.01);
        testFilm.setLength(102);
        testFilm.setReplacement_cost(34.20);
        testFilm.setRating("Spinner");
        Assertions.assertEquals(1,testFilm.getFilm_id(),"Test 'setFilm_id' failed");
        Assertions.assertEquals("Smile",testFilm.getTitle(),"Test 'setTitle' failed");
        Assertions.assertEquals("I am a Joke",testFilm.getDescription(),"Test 'setDescription' failed");
        Assertions.assertEquals(9,testFilm.getRental_duration(),"Test 'setRental_duration' failed");
        Assertions.assertEquals(10.01,testFilm.getRental_rate(),"Test 'setRental_rate' failed");
        Assertions.assertEquals(102,testFilm.getLength(),"Test 'setLength' failed");
        Assertions.assertEquals(34.20,testFilm.getReplacement_cost(),"Test 'setReplacement_cost' failed");
        Assertions.assertEquals("Spinner",testFilm.getRating(),"Test 'setRating' failed");
    }

    @Test
    void AddFilm(){
        Film testFilm = new Film(101,"WOW","POG",1,20.06,32,12.45,"wo");
        sakilaAppApplication.addFilm(testFilm);
        when(filmRepository.findById(testFilm.film_id)).thenReturn(Optional.of(testFilm));
        Optional<Film> optional = sakilaAppApplication.getFilmByID(testFilm.film_id);
        Film actual = optional.get();
        Film expected = testFilm;
        Assertions.assertEquals(expected,actual,"Testing add method");
    }
    @Test
    void TestAllCategory(){
        List<Category> categoryList = new ArrayList<>();
        Category testCategory = new Category(10,"a");
        Category testCategory2 = new Category(11,"b");
        categoryList.add(testCategory);
        categoryList.add(testCategory2);
        Iterable<Category> categoryIterable = categoryList;
        when(categoryRepository.findAll()).thenReturn(categoryIterable);
        Iterable<Category> Expected = categoryIterable;
        Iterable<Category> Actual = sakilaAppApplication.getAllCategory();
        Assertions.assertEquals(Expected, Actual, "Error");
    }
    @Test
    void TestGetFilmByCategory(){
        List<Film> filmList = new ArrayList<>();
        Film testFilm = new Film(10,"WOW","POG",1,20.06,32,12.45,"wo");
        Film testFilm2 = new Film(11,"WOW","POG",1,20.06,32,12.45,"wo");
        filmList.add(testFilm);
        filmList.add(testFilm2);
        Iterable<Film> filmIterable = filmList;
        when(filmRepository.getCategoryFilm()).thenReturn(filmIterable);
        Iterable<Film> Expected = filmIterable;
        Iterable<Film> Actual = sakilaAppApplication.getFilmByCate();
        Assertions.assertEquals(Expected,Actual,"Test 'getFilmByCate' Failed");
    }
    @Test
    void TestGetCategory(){
        Category category = new Category(10,"Home");
        Assertions.assertEquals(10,category.getCategory_id(),"Test 'getCategory_id' failed");
        Assertions.assertEquals("Home",category.getName(),"Test 'getName' failed");
    }
    @Test
    void TestSetCategory(){
        Category category = new Category();
        category.setCategory_id(20);
        category.setName("LOL");
        Assertions.assertEquals(20,category.getCategory_id(),"Test 'setCategory_id' failed");
        Assertions.assertEquals("LOL",category.getName(),"Test 'setName' failed");
    }
}
