$sig = @'
[DllImport("kernel32.dll")]
public static extern bool SetConsoleCP(uint wCodePageID);

[DllImport("kernel32.dll")]
public static extern bool SetConsoleOutputCP(uint wCodePageID);
'@

$type = Add-Type -MemberDefinition $sig -Name Win32Utils -Namespace Foo -PassThru

$type::SetConsoleCP(65001)
$type::SetConsoleOutputCP(65001)

..\gradlew :event-sourcing:clean :event-sourcing:build

java -cp "build/classes/java/main;build/resources/main/" "-Dfile.encoding=UTF-8" com.link_intersystems.es.chess.adapter.app.ChessApp