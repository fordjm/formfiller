package fitnesse.tests;

import fitnesse.junit.*;
import org.junit.runner.RunWith;

@RunWith(FitNesseRunner.class)
@FitNesseRunner.Suite("FormFiller")
@FitNesseRunner.FitnesseDir(".")
@FitNesseRunner.OutputDir("./target/fitnesse-results")
public class Tests { }