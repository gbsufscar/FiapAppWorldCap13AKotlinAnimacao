package br.com.fiap.animacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.animacao.ui.theme.AnimacaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimacaoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimacaoScreen()
                }
            }
        }
    }
}

@Composable
fun AnimacaoScreen() {

    // Variáveis de estado para contolar a visibilidade e as animações
    // -- visible: controla a visibilidade do componente
    var visible = remember {
        mutableStateOf(false)
    }

    // -- enter: controla a animação de transição de entrada
    var enter = remember {
        mutableStateOf(fadeIn())
    }

    // -- exit: controla a animação de transição de saída
    var exit = remember {
        mutableStateOf(fadeOut())
    }


    // Tela
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                /*
                Ao clicar no botão, o valor da variável visible é alterado de false para true. Assim,
                o componente box será exibido com efeito de esmaecimento (fadeIn).

                Ao clicar novamente, o valor da variável visible é alterado de true para false. Assim,
                o componente box será ocultado com efeito de esmaecimento (fadeOut).
                */
                visible.value = !visible.value // Alterna a visibilidade quando o botão é clicado (true/false)
                enter.value = fadeIn(animationSpec = tween(2000))
                exit.value = fadeOut(animationSpec = tween(2000))
            }) {
                Text(text = "Fade")
            }
            Button(onClick = {
                visible.value = !visible.value
                enter.value = slideInHorizontally(animationSpec = tween(2000))
                exit.value = slideOutHorizontally() + fadeOut(animationSpec = tween(2000))
            }) {
                Text(text = "Slide")
            }
            Button(onClick = {
            }) {
                Text(text = "Scale")
            }
            Button(onClick = {
            }) {
                Text(text = "Expand")
            }
        }
        Spacer(modifier = Modifier.height(64.dp))

        // Chamada para o componente que será animado
        BoxComponent(
            visible = visible.value,
            enter = enter.value,
            exit = exit.value
        )
    }
}

@Composable
fun BoxComponent(
    visible: Boolean,
    enter: EnterTransition,
    exit: ExitTransition
) {
    AnimatedVisibility(
        visible = visible,
        enter = enter,
        exit = exit
    ) {
        Box(modifier = Modifier
            .size(200.dp)
            .background(color = Color.Red))
    }
}