# Establecer la ubicación del directorio de trabajo
Set-Location C:\Users\pegro\OneDrive\Documentos\GitHub\RNA-403\Practicas\cagada

# Establecer la variable de entorno JAVA_HOME
$env:JAVA_HOME = "C:\Program Files\jdk-21.0.2"

# Ejecutar Maven con el plugin de ejecución
cmd /c "`"C:\Program Files\NetBeans-21\netbeans\java\maven\bin\mvn.cmd`" -Dexec.vmArgs=`"-Dexec.args=${exec.vmArgs} -classpath %classpath ${exec.mainClass} ${exec.appArgs}`" -Dexec.appArgs= -Dexec.mainClass=com.mycompany.mavenproject1.Mavenproject1 -Dexec.executable=`"C:\Program Files\jdk-21.0.2\bin\java.exe`" -Dmaven.ext.class.path=`"C:\Program Files\NetBeans-21\netbeans\java\maven-nblib\netbeans-eventspy.jar`" --no-transfer-progress process-classes org.codehaus.mojo:exec-maven-plugin:3.1.0:exec"
