package edu.metrostate.ics342.mediatracker.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.APIResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import edu.metrostate.ics342.mediatracker.data.UserRepository

class AuthViewModel(
    private val userRepository: UserRepository = UserRepository(),
) : ViewModel() {
    //returns a boolean if this is a valid email
    private fun isValidEmail(value: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$".toRegex()
        return value.matches(emailRegex)
    }

    //Should be min 8 chars, min 1 letter && 1 num
    private fun isValidPassword(value: String): Boolean {
        val passRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex()
        return value.matches(passRegex)
    }

    sealed class AuthUiState {
        object Idle    : AuthUiState()
        object Loading : AuthUiState()
        object Success : AuthUiState()
        data class Error(val msgResId: Int) : AuthUiState()
    }

    // ── Login ─────────────────────────────────────────────────────────────

    private val _email    = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _loginState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val loginState: StateFlow<AuthUiState> = _loginState.asStateFlow()

    fun onEmailChange(value: String)    { _email.value    = value }
    fun onPasswordChange(value: String) { _password.value = value }

    //TODO: add API request to login
    fun onLoginClick() {
        viewModelScope.launch {
            _loginState.value = AuthUiState.Loading
            delay(800)
            if (_email.value.isNotBlank() && _password.value.isNotBlank()) {
                _loginState.value = AuthUiState.Success
            } else {
                _loginState.value = AuthUiState.Error(edu.metrostate.ics342.mediatracker.R.string.error_empty_credentials)
            }

            var res = userRepository.login(
                _email.value,
                _password.value,
            )

            when(res) {
                is APIResult.Success -> {
                    _loginState.value = AuthUiState.Success
                }
                is APIResult.Error -> {
                    _loginState.value = AuthUiState.Error(res.msgResId);
                }
            }
        }
    }

    fun resetLoginState() { _loginState.value = AuthUiState.Idle }

    // ── Registration ──────────────────────────────────────────────────────
    private val _display = MutableStateFlow("")
    val display: StateFlow<String> = _display.asStateFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _passconfirm = MutableStateFlow("")
    val passwordConfirm: StateFlow<String> = _passconfirm.asStateFlow()

    fun onDisplayChange(value: String)    { _display.value    = value }
    fun onUsernameChange(value: String) { _username.value = value }
    fun onPasswordConfirmChange(value: String) { _passconfirm.value = value }

    private val _registerState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val registerState: StateFlow<AuthUiState> = _registerState.asStateFlow()

    fun onRegisterClick() {
        viewModelScope.launch {
            _registerState.value = AuthUiState.Loading

            //validate each field
            val validationRules = listOf(
                { _display.value.isBlank() } to edu.metrostate.ics342.mediatracker.R.string.error_empty_display,
                { _username.value.isBlank() } to edu.metrostate.ics342.mediatracker.R.string.error_empty_user,
                { _email.value.isBlank() || !isValidEmail(_email.value) } to edu.metrostate.ics342.mediatracker.R.string.error_empty_email,
                { _password.value.isBlank() } to edu.metrostate.ics342.mediatracker.R.string.error_empty_pass,
                { _passconfirm.value.isBlank() } to edu.metrostate.ics342.mediatracker.R.string.error_empty_pass,
                { _passconfirm.value != _password.value } to edu.metrostate.ics342.mediatracker.R.string.error_pass_not_match,
                { !isValidPassword(_passconfirm.value) } to edu.metrostate.ics342.mediatracker.R.string.error_bad_password //only need to test one pass
            )

            //Finds the first invalid item
            val error = validationRules.firstOrNull { it.first() }?.second
            if (error != null) {
                _registerState.value = AuthUiState.Error(error)
                return@launch
            }

            var res = userRepository.createAccount(
                _display.value,
                _username.value,
                _email.value,
                _password.value,
            )

            when(res) {
                is APIResult.Success -> {
                    _registerState.value = AuthUiState.Success
                }
                is APIResult.Error -> {
                    _registerState.value = AuthUiState.Error(res.msgResId);
                }
            }
        }
    }

    fun resetRegisterState() { _registerState.value = AuthUiState.Idle }
}
