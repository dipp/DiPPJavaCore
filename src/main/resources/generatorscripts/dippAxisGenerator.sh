#!/bin/bash
echo "wsdl Generator for DiPP, using Axis Version 1.4."

export CODEGEN_CACHE=~/axisCodeGenerator/dippCore
rm -R $CODEGEN_CACHE/resources/
rm -R $CODEGEN_CACHE/src/
rm -R $CODEGEN_CACHE/dippdoc.wsdl



cp ~/git/dippCoreMvn/src/main/resources/wsdl/dippdoc.wsdl $CODEGEN_CACHE/dippdoc.wsdl 

export AXIS_HOME=/opt/axis-1_4
export AXIS_LIB=$AXIS_HOME/lib
export AXISCLASSPATH="$AXIS_LIB/axis.jar:$AXIS_LIB/commons-discovery-0.2.jar:$AXIS_LIB/commons-logging-1.0.4.jar:$AXIS_LIB/jaxrpc.jar:$AXIS_LIB/saaj.jar:$AXIS_LIB/log4j-1.2.8.jar:$AXIS_LIB/xml-apis.jar:$AXIS_LIB/xercesImpl.jar:$AXIS_LIB/wsdl4j-1.5.1.jar"

java -cp $AXISCLASSPATH org.apache.axis.wsdl.WSDL2Java -s -v --skeletonDeploy true --allowInvalidURL -B $CODEGEN_CACHE/dippdoc.wsdl

# copy impl file to generator dir to avoid getting an empty impl stub
cp ~/git/dippCoreMvn/src/main/java/de/nrw/dipp/dippCore/www/definitions/DippSoapBindingImpl.java $CODEGEN_CACHE/de/nrw/dipp/dippCore/www/definitions/

cp -f $CODEGEN_CACHE/de/nrw/dipp/dippCore/webservice/*.* ~/git/dippCoreMvn/src/main/java/de/nrw/dipp/dippCore/webservice/ 
cp -f $CODEGEN_CACHE/de/nrw/dipp/dippCore/www/definitions/*.* ~/git/dippCoreMvn/src/main/java/de/nrw/dipp/dippCore/www/definitions/
