package com.marcokosan.pagsegurotest.archframework

import java.io.IOException

class NoConnectionException : IOException()

class ApiException(val httpStatus: Int) : IOException()