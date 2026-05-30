package edu.metrostate.ics342.mediatracker.ui.auth

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var display = rememberTextFieldState("");
    var username = rememberTextFieldState("");
    var email = rememberTextFieldState("");

    //Maybe use Hash?
    var password = rememberTextFieldState("");
    var passConf = rememberTextFieldState("");

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        Arrangement.Center,
       Alignment.CenterHorizontally
    ) {
        //TODO: Missing Ttile Image

        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary //TODO: must be text primary (black)
        )
        Text(
            text = "Join the community",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.secondary //TODO: must be text secondary (grey)
        )
        Spacer(Modifier.height(24.dp))

        //Actual Login Items - Input Fields
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = display,
            label = { Text("Display Name") }
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = username,
            label = { Text("Username") }
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = email,
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.height(24.dp))

        //TODO: Password Should Be Hidden
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = password,
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = passConf,
            label = { Text("Confirm Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onRegisterSuccess, //TODO: Should have func to actually sign-up instead of just saying it's OK - validation
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Text("Sign Up")
        }
        Spacer(Modifier.height(8.dp))
        TextButton(onClick = onNavigateToLogin) {
            Text("Already have an account? Log In") //TODO: The button should only be the 'Sign In' Part, not whole part
        }
    }
}
