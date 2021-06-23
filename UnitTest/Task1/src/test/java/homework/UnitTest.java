package homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class UnitTest {

    Coordinates coordinates;
    Unit unit;

    @BeforeEach
    void initUnit(){
        coordinates = new Coordinates(0,0);
        unit = new Unit(coordinates,50,20);
    }

    @Test
    void ifFuelIsHigherThanSumOfXYThenShouldReturnNewCoordinates(){
        //gives
        for (int i=0; i>40; i=unit.getFuel()) unit.tankUp();
        coordinates = unit.move(30,12);
        //then
        assertThat(coordinates).isEqualTo(new Coordinates(30,12));
    }

    @Test
    void ifFuelIsLowerThanSumOfXYThenMoveShouldThrowException(){
        //then
        assertThrows(IllegalStateException.class, ()-> unit.move(30,23));
    }

    @Test
    void fuelShouldBeEqualToMaxFuelAfterTankUp(){
        //gives
        for (int i=0; i>=50; i=unit.getFuel()) unit.tankUp();
        //then
        assertThat(unit.getFuel()).isEqualTo(50);
    }

    @Test
    void ifWeightOfAllCargosIsLowerThanMaxCargoUnitShouldReturnSumOfCargosWeight(){
        //when
        int weight = 6;
        //gives
        for (int i = 0 ; i<3; i++){
            unit.loadCargo(new Cargo("cargo "+i,weight));
        }
        //then
        assertThat(unit.getLoad()).isEqualTo(weight*3);
    }

    @Test
    void ifCargosWeightIsHigherThanMaxCargoUnitShouldThrowException(){
        //when
        int weight = 6;
        //gives
        for (int i = 0 ; i<3; i++){
            unit.loadCargo(new Cargo("cargo "+i,weight));
        }
        //then
        assertThrows(IllegalStateException.class,()->unit.loadCargo(new Cargo("overload cargo", weight)));
    }

    @Test
    void UnitLoadShouldBeEmptyAfterUnloadAllCargo(){
        //when
        int weight = 6;
        //gives
        for (int i = 0 ; i<3; i++){
            unit.loadCargo(new Cargo("cargo "+i,weight));
        }
        unit.unloadAllCargo();
        //then
        assertThat(unit.getLoad()).isEqualTo(0);
    }

    @Test
    void unitLoadShouldHaveWeightOfOnlyTwoCargos(){
        //when
        int weight = 6;
        for (int i = 0 ; i<2; i++){
            unit.loadCargo(new Cargo("cargo "+i,weight));
        }
        Cargo cargo = new Cargo("cargo to unload", weight);
        unit.loadCargo(cargo);
        //gives
        unit.unloadCargo(cargo);
        //then
        assertThat(unit.getLoad()).isEqualTo(weight*2);
    }
}