# jlink 打包 
jlink --module-path . --add-modules java.base,jdk.httpserver,TestJPackage  --output myjre   --launcher mingtest=TestJPackage/com.Ming
# jpackage 打包   
jpackage --type deb -n mingtest -m TestJPackage/com.Ming --runtime-image myjre 