package org.koin.core

import org.junit.Assert.*
import org.junit.Test
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.DefinitionParameters.Companion.MAX_PARAMS
import org.koin.core.parameter.parametersOf

class ParametersHolderTest {

    @Test
    fun `create a parameters holder - 1 param`() {
        val myString = "empty"
        val myInt = 42
        val params = parametersOf(myString)
        val newParams = params.insert(0,myInt)

        assertEquals(2, newParams.size())
        assertTrue(newParams.get<Int>(0) == myInt)
        assertTrue(newParams.get<String>(1) == myString)
    }

    @Test
    fun `create a parameters holder - 2 params`() {
        val myString = "empty"
        val myInt = 42
        val params = parametersOf(myString,myInt)
        val newParams = params.insert(0,myInt)

        assertEquals(3, newParams.size())
        assertTrue(newParams.get<Int>(0) == myInt)
        assertTrue(newParams.get<String>(1) == myString)
    }

    @Test
    fun `create a parameters holder`() {
        val myString = "empty"
        val myInt = 42
        val parameterHolder: DefinitionParameters = parametersOf(myString, myInt)

        assertEquals(2, parameterHolder.size())
        assertTrue(parameterHolder.isNotEmpty())
    }

    @Test
    fun `create an empty parameters holder`() {
        val parameterHolder: DefinitionParameters = parametersOf()

        assertEquals(0, parameterHolder.size())
        assertTrue(parameterHolder.isEmpty())
    }

    @Test
    fun `get parameters from a parameter holder`() {
        val myString = "empty"
        val myInt = 42
        val parameterHolder: DefinitionParameters = parametersOf(myString, myInt)

        val (s: String, i: Int) = parameterHolder
        assertEquals(myString, s)
        assertEquals(myInt, i)
    }

    @Test
    fun `can't create parameters more than max params`() {
        try {
            parametersOf(1, 2, 3, 4, 5, 6)
            fail("Can't build more than $MAX_PARAMS")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun `can insert param`() {
        val p = parametersOf(1, 2, 3, 4)
        assert(p.insert(4, 5).get<Int>(4) == 5)
        assert(p.insert(0, 0).get<Int>(0) == 0)
    }

    @Test
    fun `can't insert param`() {
        val p = parametersOf(1, 2, 3, 4, 5)
        try {
            assert(p.insert(0, 0).get<Int>(0) == 0)
            fail()
        } catch (e: Exception) {

        }
    }
}