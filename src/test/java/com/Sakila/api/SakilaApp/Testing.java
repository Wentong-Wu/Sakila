package com.Sakila.api.SakilaApp;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

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

}
