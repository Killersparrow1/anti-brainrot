package com.brainrot.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navGraph

sealed interface AppScreen {
    // Auth
    data class Onboarding(val step: Int = 0) : AppScreen
    object Login : AppScreen
    object Permissions : AppScreen

    // Main tabs
    object Home : AppScreen
    object Progress : AppScreen
    object Battle : AppScreen
    object Profile : AppScreen

    // Features
    data class StatsDetail(val date: String?) : AppScreen
    data class WeeklyStory(val week: String?) : AppScreen
    object ReelsWrapped : AppScreen
    object BattleDetail : AppScreen
    object InviteFriends : AppScreen
    object Settings : AppScreen
    object Subscription : AppScreen
    object Paywall : AppScreen
    object Feedback : AppScreen
    object About : AppScreen

    // Deep links
    data class InviteLink(val code: String) : AppScreen
    data class BattleInvite(val battleId: String) : AppScreen
    data class ShareWrapped(val week: String) : AppScreen
}

fun NavGraphBuilder.appNavGraph(startDestination: AppScreen = AppScreen.Home) {
    navigation(startDestination = startDestination, route = "app") {
        composable<AppScreen.Onboarding> { backStackEntry ->
            val screen = backStackEntry.toRoute<AppScreen.Onboarding>()
            OnboardingScreen(step = screen.step)
        }
        composable<AppScreen.Login> { LoginScreen() }
        composable<AppScreen.Permissions> { PermissionsScreen() }

        composable<AppScreen.Home> { HomeScreen() }
        composable<AppScreen.Progress> { ProgressScreen() }
        composable<AppScreen.Battle> { BattleScreen() }
        composable<AppScreen.Profile> { ProfileScreen() }

        composable<AppScreen.StatsDetail> { backStackEntry ->
            val screen = backStackEntry.toRoute<AppScreen.StatsDetail>()
            StatsDetailScreen(date = screen.date)
        }
        composable<AppScreen.WeeklyStory> { backStackEntry ->
            val screen = backStackEntry.toRoute<AppScreen.WeeklyStory>()
            WeeklyStoryScreen(week = screen.week)
        }
        composable<AppScreen.ReelsWrapped> { ReelsWrappedScreen() }
        composable<AppScreen.BattleDetail> { BattleDetailScreen() }
        composable<AppScreen.InviteFriends> { InviteFriendsScreen() }
        composable<AppScreen.Settings> { SettingsScreen() }
        composable<AppScreen.Subscription> { SubscriptionScreen() }
        composable<AppScreen.Paywall> { PaywallScreen() }
        composable<AppScreen.Feedback> { FeedbackScreen() }
        composable<AppScreen.About> { AboutScreen() }

        // Deep links
        composable<AppScreen.InviteLink> { backStackEntry ->
            val screen = backStackEntry.toRoute<AppScreen.InviteLink>()
            InviteLinkScreen(code = screen.code)
        }
        composable<AppScreen.BattleInvite> { backStackEntry ->
            val screen = backStackEntry.toRoute<AppScreen.BattleInvite>()
            BattleInviteScreen(battleId = screen.battleId)
        }
        composable<AppScreen.ShareWrapped> { backStackEntry ->
            val screen = backStackEntry.toRoute<AppScreen.ShareWrapped>()
            ShareWrappedScreen(week = screen.week)
        }
    }
}

// Placeholder composables
@Composable
fun OnboardingScreen(step: Int) { Box(modifier = Modifier.fillMaxSize()) { Text("Onboarding Step $step") } }

@Composable
fun LoginScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Login") } }

@Composable
fun PermissionsScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Permissions") } }

@Composable
fun HomeScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Home") } }

@Composable
fun ProgressScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Progress") } }

@Composable
fun BattleScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Battle") } }

@Composable
fun ProfileScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Profile") } }

@Composable
fun StatsDetailScreen(date: String?) { Box(modifier = Modifier.fillMaxSize()) { Text("Stats Detail: $date") } }

@Composable
fun WeeklyStoryScreen(week: String?) { Box(modifier = Modifier.fillMaxSize()) { Text("Weekly Story: $week") } }

@Composable
fun ReelsWrappedScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Reels Wrapped") } }

@Composable
fun BattleDetailScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Battle Detail") } }

@Composable
fun InviteFriendsScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Invite Friends") } }

@Composable
fun SettingsScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Settings") } }

@Composable
fun SubscriptionScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Subscription") } }

@Composable
fun PaywallScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Paywall") } }

@Composable
fun FeedbackScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("Feedback") } }

@Composable
fun AboutScreen() { Box(modifier = Modifier.fillMaxSize()) { Text("About") } }

@Composable
fun InviteLinkScreen(code: String) { Box(modifier = Modifier.fillMaxSize()) { Text("Invite: $code") } }

@Composable
fun BattleInviteScreen(battleId: String) { Box(modifier = Modifier.fillMaxSize()) { Text("Battle Invite: $battleId") } }

@Composable
fun ShareWrappedScreen(week: String) { Box(modifier = Modifier.fillMaxSize()) { Text("Share Wrapped: $week") } }