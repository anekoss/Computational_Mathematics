import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.lang.StrictMath.sin;
import static java.lang.StrictMath.pow;

class Result {

    private static double first_function(List<Double> args) {
        return sin(args.get(0));
    }

    private static double second_function(List<Double> args) {
        return (args.get(0) * args.get(1)) / 2;
    }

    private static double third_function(List<Double> args) {
        return
                pow(args.get(0), 2) * pow(args.get(1), 2) - 3 * pow(args.get(0), 3) - 6 * pow(args.get(1),
                        3) + 8;
    }

    private static double fourth_function(List<Double> args) {
        return pow(args.get(0), 4) - 9 * args.get(1) + 2;
    }

    private static double fifth_function(List<Double> args) {
        return args.get(0) + pow(args.get(0), 2) - 2 * args.get(1) * args.get(2) - 0.1;
    }

    private static double six_function(List<Double> args) {
        return args.get(1) + pow(args.get(1), 2) + 3 * args.get(0) * args.get(2) + 0.2;
    }


    private static double seven_function(List<Double> args) {
        return args.get(2) + pow(args.get(2), 2) + 2 * args.get(0) * args.get(1) - 0.3;
    }

    private static double default_function(List<Double> args) {
        return 0.0;
    }

    /*
     * How to use this function:
     *    List<Function<Double, List<Double>> funcs = get_functions(4);
     *    funcs[0].apply(List.of(0.0001, 0.002));
     */
    private static List<Function<List<Double>, Double>> get_functions(int n) {
        switch (n) {
            case (1):
                return List.of(Result::first_function, Result::second_function);
            case (2):
                return List.of(Result::third_function, Result::fourth_function);
            case (3):
                return List.of(Result::fifth_function, Result::six_function, Result::seven_function);
            default:
                return List.of(Result::default_function);
        }
    }
    private static List<Double> getNextByIterations(int number_of_unknowns, List<Double> initial_approximations, List<Function<List<Double>, Double>> funcs) {
        List<Double> res = new ArrayList<>(number_of_unknowns);
        for (int i = 0; i < number_of_unknowns; i++) {
            res.add(i, funcs.get(i).apply(initial_approximations));
            if (res.get(i) == Double.POSITIVE_INFINITY || res.get(i) == Double.NEGATIVE_INFINITY || res.get(i) == Double.NaN) {
                return initial_approximations;
            }
        }
        return res;
    }

    private static boolean isEpsilonSatisfied(List<Double> cur, List<Double> result) {
        boolean ret = true;
        for (int i = 0; i < cur.size(); i++) {
            if (Math.abs(cur.get(i) - result.get(i)) > 0.00001) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    public static List<Double> solve_by_fixed_point_iterations(int system_id, int number_of_unknowns, List<Double> initial_approximations) {
        List<Function<List<Double>, Double>> funcs = get_functions(system_id);
        List<Double> result = initial_approximations;
        while (!isEpsilonSatisfied(getNextByIterations(number_of_unknowns, result, funcs), result)) {
            List<Double> cur = result;
            result = getNextByIterations(number_of_unknowns, result, funcs);
            for (int i = 0; i < number_of_unknowns; i++) {
                cur.set(i, Math.abs(result.get(i) - getNextByIterations(number_of_unknowns, result, funcs).get(i)));
                if(cur ==result) return result;
            }
        }
        return result;
    }}

/*
 * Complete the 'solve_by_fixed_point_iterations' function below.
 *
 * The function is expected to return a DOUBLE_ARRAY.
 * The function accepts following parameters:
 *  1. INTEGER system_id
 *  2. INTEGER number_of_unknowns
 *  3. DOUBLE_ARRAY initial_approximations
 */
