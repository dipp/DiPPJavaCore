#!/bin/bash
echo "wsdl Generator for DiPP, using Axis Version 1.4."

export CODEGEN_CACHE=~/axisCodeGenerator
rm -R $CODEGEN_CACHE/pdfAtool/resources/
rm -R $CODEGEN_CACHE/pdfAtool/src/
rm -R $CODEGEN_CACHE/pdfAtool/*.*


cp ~/git/dippcore/src/main/resources/wsdl/dippdoc.wsdl $CODEGEN_CACHE/dippCore/dippdoc.wsdl 

export AXIS_BIN=/opt/axis-1_4/bin
export AXIS_HOME=/opt/axis2-1.4.1/

$AXIS2_BIN/wsdl2java.sh -u -uri $CODEGEN_CACHE/pdfAtool/PdfAConverter.wsdl -o $CODEGEN_CACHE/pdfAtool
$AXIS2_BIN/wsdl2java.sh -ss -ssi -uri $CODEGEN_CACHE/pdfAtool/PdfAConverter.wsdl -o $CODEGEN_CACHE/pdfAtool 

cp -f $CODEGEN_CACHE/pdfAtool/src/de/hbz_nrw/www/pdfaconverter/services/*.* ~/git/pdfaWS/src/main/java/de/hbz_nrw/www/pdfaconverter/services 
cp -f $CODEGEN_CACHE/pdfAtool/src/de/hbz_nrw/www/pdfaconverter/types/*.* ~/git/pdfaWS/src/main/java/de/hbz_nrw/www/pdfaconverter/types
