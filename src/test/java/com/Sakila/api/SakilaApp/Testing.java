package com.Sakila.api.SakilaApp;

import com.google.common.collect.Iterables;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        sakilaAppApplication.deleteFilm(10);
        verify(filmRepository).deleteById(testFilm.getFilm_id());
        //Test to see if the function is being called. Does not test the data to see if it is actually deleted.
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
