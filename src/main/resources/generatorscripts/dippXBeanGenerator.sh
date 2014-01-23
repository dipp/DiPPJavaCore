#!/bin/bash
echo "xbeanGenerator for DiPP, using xmlbeans Version 1.0.4"

export CODEGEN_CACHE=~/xbeanGenerator/dippCore
#rm -R $CODEGEN_CACHE/resources/
#rm -R $CODEGEN_CACHE/src/
#rm -R $CODEGEN_CACHE/dippdoc.wsdl


cp -R ~/git/dippCoreMvn/src/main/resources/xsd/dublincore/ $CODEGEN_CACHE

export XMLBEANS_HOME=/opt/xmlbeans-1.0.4


$XMLBEANS_HOME/bin/scomp -verbose -dl -out $CODEGEN_CACHE/qdcMetadata-1.6.jar $CODEGEN_CACHE/dublincore/*.xsd


#cp -f $CODEGEN_CACHE/de/nrw/dipp/dippCore/webservice/*.* ~/git/dippCoreMvn/src/main/java/de/nrw/dipp/dippCore/webservice/ 
#cp -f $CODEGEN_CACHE/de/nrw/dipp/dippCore/www/definitions/*.* ~/git/dippCoreMvn/src/main/java/de/nrw/dipp/dippCore/www/definitions/
