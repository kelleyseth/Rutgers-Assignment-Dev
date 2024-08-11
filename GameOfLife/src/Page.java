// Enum class for different pages
// Note: An enum is a better way have having constants in code bc it avoids using "magic strings" and it has type safety
//       Type safety meaning if I try to use a Page object that isn't one of the three below, Java won't let me
public enum Page {
    CONSTRUCTOR, INPUT, CREATE, METHOD;
}