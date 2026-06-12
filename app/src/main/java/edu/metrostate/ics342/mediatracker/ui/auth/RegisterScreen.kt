package edu.metrostate.ics342.mediatracker.ui.auth

import android.inputmethodservice.Keyboard
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.metrostate.ics342.mediatracker.theme.OnPrimaryContainer
import edu.metrostate.ics342.mediatracker.theme.PrimaryContainer

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val display         by viewModel.display.collectAsState()
    val username        by viewModel.username.collectAsState()
    val email           by viewModel.email.collectAsState()
    val password        by viewModel.password.collectAsState()
    val passConf        by viewModel.passwordConfirm.collectAsState()

    val registerState      by viewModel.registerState.collectAsState()
    val focusManager = LocalFocusManager.current

    // Navigate on success
    LaunchedEffect(registerState) {
        if (registerState is AuthViewModel.AuthUiState.Success) {
            viewModel.resetRegisterState()
            onRegisterSuccess()
        }
    }

    val isLoading = registerState is AuthViewModel.AuthUiState.Loading
    val errorMsg  = (registerState as? AuthViewModel.AuthUiState.Error)?.msgResId?.let { stringResource(it) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        Arrangement.Center,
       Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(edu.metrostate.ics342.mediatracker.R.drawable.smart_display),
            contentDescription = "Application Icon",
            modifier = Modifier
                .size(width = 64.dp, height = 64.dp)
                .background(PrimaryContainer, RoundedCornerShape(12.dp))
                .padding(12.dp),
            colorFilter = ColorFilter.tint(OnPrimaryContainer)
        )

        Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_title), style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary)

        Spacer(Modifier.height(8.dp))

        Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_sub_title),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center)

        Spacer(Modifier.height(40.dp))

        //Actual Login Items - Input Fields
        OutlinedTextField(
            value         = display,
            onValueChange = viewModel::onDisplayChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.displayname_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value         = username,
            onValueChange = viewModel::onUsernameChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.username_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value         = email,
            onValueChange = viewModel::onEmailChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.email_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))

        //TODO: Password Should Be Hidden
        OutlinedTextField(
            value         = password,
            onValueChange = viewModel::onPasswordChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.password_label)) },
            singleLine    = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value         = passConf,
            onValueChange = viewModel::onPasswordConfirmChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.confirm_password_label)) },
            singleLine    = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction    = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMsg != null) {
            Spacer(Modifier.height(8.dp))
            Text(errorMsg, color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall)
        }

        Spacer(Modifier.height(24.dp))
        Button(
            onClick  = { focusManager.clearFocus(); viewModel.onRegisterClick() },
            enabled  = !isLoading,
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.sign_up_button))
            }
        }

        Spacer(Modifier.height(16.dp))

        TextButton(onClick = onNavigateToLogin) {
            Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.login_prompt))
        }
    }
}
