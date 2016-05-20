//package com.Evolution.fuzz;
//
//import java.lang.reflect.Parameter;
//import java.util.Random;
//
//
//import com.Evolution.exceptions.NullGameObjectException;
//import com.Evolution.interfaces.IPlayer;
//import com.Evolution.logic.*;
//import org.junit.Test;
//
//import java.lang.reflect.Array;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//
//public class GameFuzzTests {
//
//    private Game g;
//    private Random rn = new Random();
//
//    private ArrayList<IPlayer> createPlayers() throws Throwable{
//        ArrayList<IPlayer> players = new ArrayList<>();
//        int j = 3 + (int)(Math.random() * ((5 - 3) + 1));
//        for(int i = 0; i < j; i ++){
//            players.add(new Player(new Species()));
//            int k = 0 + (int)(Math.random() * ((1000 - 0) + 1));
//            for(int z = 0; z < k; z++){
//                players.get(i).addSpeciesRight(new Species());
//            }
//        }
//        return players;
//    }
//
//
//    public GameFuzzTests() throws Throwable {
//        Deck drawpile = new Deck();
//        for(int i = 0; i < 100; i++){
//            drawpile.add(new Card("","","",0,0));
//        }
//        this.g = new Game(createPlayers(), new WateringHole(), drawpile, new Deck<>());
//    }
//
//    private Object[] createArguments(Object[] params) throws IllegalAccessException, InstantiationException {
//        for(int i=0; i< params.length; i++){
//            params[i] = params[i].getClass().newInstance();
//        }
//        return params;
//    }
//
//    private Class<?>[] createArgumentTypes(Object[] arguments) {
//        Class[] types = new Class[arguments.length];
//        for (int i = 0; i < arguments.length; i++) {
//            types[i] = arguments[i].getClass();
//        }
//        return types;
//    }
//
//    @Test
//    public void noErrors() throws InvocationTargetException, IllegalAccessException, InstantiationException {
//        Method[] m = this.g.getClass().getMethods();
//        int methodNum = 0 + (int)(Math.random() * ((44 - 0) + 1));
//        for(int i=0; i<100; i++){
////            Method method = instance.getClass().getMethod(sMethod, argumentTypes );
////            return method.invoke(instance, argumentsWithSession);
//            Parameter[] params = m[i].getParameters();
//            Object[] arguments = createArguments(params);
//            Class<?>[] argumentTypes = createArgumentTypes(params);
//            m[i].invoke(arguments);
//        }
//    }
//
//}