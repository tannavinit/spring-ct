package com.springct.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    isError: Boolean,
    errorMessage: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange, label = {
                Text(text = label)
            },
            maxLines = 1,
            singleLine = true,
            visualTransformation = visualTransformation
        )

        if (isError) {
            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            Text(
                text = errorMessage,
                style = TextStyle(color = Color.Red)
            )
        }

    }


}