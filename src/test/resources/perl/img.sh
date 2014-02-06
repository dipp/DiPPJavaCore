#!/bin/sh
#
# this script calls ImageMagick-scripts to resize an image
# created by schirrwagen@hbz-nrw.de
# 21-02-2007
#
absSrcPath="$1"
srcFileName="$2"
width="$3"
height="$4"

resize="/usr/bin/convert"

mv "$absSrcPath"/"$srcFileName" "$absSrcPath"/orig_"$srcFileName"
"$resize" "$absSrcPath"/orig_"$srcFileName" -resize "$width"x"$height"\> "$absSrcPath"/"$srcFileName"
exit $?
