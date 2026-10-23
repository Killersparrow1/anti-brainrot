# Contributing to Anti-BrainRot

Thank you for your interest in contributing to Anti-BrainRot! This document provides guidelines and best practices for contributing to this **fully offline**, privacy-first Android application.

## Code of Conduct

By participating in this project, you agree to abide by our [Code of Conduct](CODE_OF_CONDUCT.md). Please read it before contributing.

## How to Contribute

### Reporting Bugs

1. **Search existing issues** first to avoid duplicates
2. **Create a new issue** with:
   - Clear, descriptive title
   - Steps to reproduce
   - Expected vs actual behavior
   - Device/OS version
   - App version
   - Screenshots/logs if applicable

### Suggesting Features

1. **Check existing feature requests** first
2. **Create a feature request issue** with:
   - Clear description of the feature
   - Use cases and benefits
   - Mockups or wireframes (if UI-related)
   - Implementation considerations
   - **Must work offline** - no network dependencies

### Pull Requests

1. **Fork the repository**
2. **Create a feature branch** from `main`:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes** following our guidelines
4. **Run tests and lint**:
   ```bash
   ./gradlew test lint ktlintCheck
   ```
5. **Commit with clear messages**:
   ```
   feat: add weekly statistics screen
   
   - Add WeeklyStatsFragment with charts
   - Implement WeeklyStatsViewModel
   - Add navigation entry
   - Update strings and themes
   ```
6. **Push and create PR** with:
   - Clear title and description
   - Reference related issues
   - Screenshots for UI changes

## Development Guidelines

### Code Style

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use `ktlint` for formatting: `./gradlew ktlintFormat`
- Maximum line length: 120 characters
- Use meaningful variable/function names
- Prefer `val` over `var`
- Use coroutines for async operations

### Architecture

- Follow **MVVM** with **Clean Architecture**
- Use **Unidirectional Data Flow** (StateFlow/LiveData)
- Separate concerns: UI, Domain, Data layers
- Use **Hilt** for dependency injection
- Write **repository interfaces** in domain, implementations in data
- **No network layer** - all data local (Room, DataStore)

### Testing

- Write **unit tests** for ViewModels, UseCases, Repositories
- Write **UI tests** for critical user flows
- Target **80%+ code coverage** for new code
- Use **MockK** for mocking
- Test edge cases and error states

### Commits

Follow [Conventional Commits](https://www.conventionalcommits.org/):

| Type | Description |
|------|-------------|
| `feat` | New feature |
| `fix` | Bug fix |
| `docs` | Documentation changes |
| `style` | Code style changes (formatting, etc.) |
| `refactor` | Code refactoring |
| `test` | Adding/updating tests |
| `chore` | Maintenance tasks |
| `perf` | Performance improvements |

Examples:
```
feat: add battle invitation flow
fix: resolve crash on Android 14 widget update
docs: update README with build instructions
refactor: extract reel detection logic to separate class
```

### Branch Naming

- `feature/description` - New features
- `fix/description` - Bug fixes
- `docs/description` - Documentation
- `refactor/description` - Refactoring
- `chore/description` - Maintenance

## Project Structure

```
app/src/main/java/com/brainrot/android/
├── core/                 # Base classes, DI, navigation
├── feature_battle/       # Local battle mode (offline)
├── feature_fallback/     # Fallback counter (Usage Stats)
├── feature_feedback/     # Local feedback (no network)
├── feature_invite/       # Local invite sharing
├── feature_pace_nudge/   # Scroll pace detection
├── feature_permission/   # Permission management
├── feature_reels_counter/# Core counting logic
├── feature_stats/        # Analytics, charts, wrapped (local)
├── feature_weekly_story/ # Weekly recap
├── feature_widget/       # Glance widgets
├── floating_bubble/      # Overlay service
├── navigation/           # Navigation graph
├── services/             # Accessibility, foreground
└── workers/              # WorkManager tasks
```

## Adding New Features

1. **Create feature module** under `feature_*`
2. **Define domain layer** (interfaces, models, use cases)
3. **Implement data layer** (repository, database - **local only**)
4. **Build UI layer** (Compose screens, ViewModels)
5. **Add navigation** entry in `AppNavigation`
6. **Add strings** in `res/values/strings.xml`
7. **Write tests** for all layers
8. **Update documentation**

### Offline-First Requirements

- ❌ No network calls (Retrofit, OkHttp, Firebase, etc.)
- ❌ No cloud sync, auth, or analytics
- ❌ No third-party SDKs requiring internet
- ✅ Local Room database for persistence
- ✅ DataStore for preferences
- ✅ WorkManager for background tasks
- ✅ Local notifications only

## Localization

- All user-facing strings in `res/values/strings.xml`
- Support RTL layouts
- Test with pseudolocales
- Add translations for supported languages

## Accessibility

- Content descriptions for all interactive elements
- Sufficient contrast ratios (4.5:1 minimum)
- Support TalkBack and Switch Access
- Test with Accessibility Scanner

## Performance

- Profile with Android Studio Profiler
- Avoid main thread work
- Use `LazyColumn`/`LazyRow` for lists
- Minimize overdraw
- Optimize database queries

## Security (Offline Context)

- No API keys/secrets needed (no network)
- Validate all user inputs
- No external data sources
- Follow [OWASP Mobile Top 10](https://owasp.org/www-project-mobile-top-10/)
- ProGuard/R8 obfuscation in release builds

## Review Process

1. **Automated checks** must pass (CI)
2. **Code review** by maintainer
3. **Address feedback** promptly
4. **Squash commits** before merge
5. **Delete branch** after merge

## Release Process

1. Version bump in `build.gradle.kts`
2. Update `CHANGELOG.md`
3. Create release tag
4. GitHub Actions builds **APK**
5. Announce in discussions

## Getting Help

- **Discussions**: [GitHub Discussions](https://github.com/yourusername/anti-brainrot/discussions)
- **Email**: contributors@anti-brainrot.app

## Recognition

Contributors are recognized in:
- [CONTRIBUTORS.md](CONTRIBUTORS.md)
- Release notes
- App "About" screen

Thank you for making Anti-BrainRot better! 🧠✨