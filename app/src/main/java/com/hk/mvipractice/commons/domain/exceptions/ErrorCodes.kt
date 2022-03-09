package com.hk.mvipractice.commons.domain.exceptions

/**
 * As a holder for codes and still having the Enum functionality to make sure the checker has
 * covered all the possible cases
 *
 * @property code
 */
sealed class ErrorCodes(val code: Int) {
    class NoConnectionCode : ErrorCodes(-2)
    class LocationErrorCode : ErrorCodes(-88)
    class NotAuthorizedCode : ErrorCodes(401)
    class SomethingWentWrongCode : ErrorCodes(500)
    class UnknownCode(errorCode: Int) : ErrorCodes(errorCode)
}