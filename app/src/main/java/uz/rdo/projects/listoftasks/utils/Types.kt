package uz.rdo.projects.listoftasks.utils

/**
 * Created by Davronbek Raximjanov 17-Feb-21
 **/

typealias SingleBlock <T> = (T) -> Unit
typealias DoubleBlock <T, E> = (T, E) -> Unit
typealias TrialBlock <T, E, F> = (T, E, F) -> Unit
typealias EmptyBlock = () -> Unit
