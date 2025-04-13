package ie.setu.cloudbalance_00.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IamRoleGuideScreen(
    onNavigateToInput: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Step-by-Step: Getting Your IAM Role ARN", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        val steps = listOf(
            "1. Access the AWS IAM Console\n→ Navigate to https://console.aws.amazon.com/iam",
            "2. Create a New Role\n→ Select \"Another AWS account\" as the trusted entity\n→ Enter the Cloud Balance AWS account ID\n→ Specify external ID: \"cloud-balance-dev\"",
            "3. Attach Permissions\n→ Attach ReadOnlyAccess or create a policy with:\n   - ce:GetCostAndUsage\n   - ec2:DescribeInstances",
            "4. Finalise Role\n→ Name the role (e.g., CloudBalanceReadOnlyRole)\n→ Copy the full Role ARN",
            "5. Input ARN into the App\n→ Tap below to continue to the input screen"
        )

        steps.forEach { step ->
            Text(step)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Button(
            onClick = onNavigateToInput,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Go to IAM Role Input")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onNavigateBack,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Back to Home")
        }
    }
}

