<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xlink="http://www.w3.org/1999/xlink" 
    xmlns:html="http://www.w3.org/HTML/1998/html4"
    xmlns:my-class="xalan://de.infinityloop.upcast.exportfilters.ExportFilter11" 
    xmlns:xalan="http://xml.apache.org/xalan"
    xmlns:mml="http://www.w3.org/1998/Math/MathML"
    exclude-result-prefixes="my-class html xlink xalan mml" >

<!-- **************************************************
     XSLT stylesheet to convert an internal upCast tree
                     to DocBook 4.2.
                     
                     Used in ExportFilter11 (DocBook42).
                     
     DISCLAIMER:
       This stylesheet is provided AS IS with no warranty.
       Use completely at your own risk!
                       
     ******

     Author :  Christian Roth <roth@infinity-loop.de>
     Version:  1.6
     CVS    :  $Id: db42.xsl,v 1.5.2.6 2005/04/06 16:14:19 iloop Exp $
     
     ******

     Notes:
     1.0  [upCast 5.0.0]
          - Initial release for upCast 5.0.0
     
     1.1  [2004-05-12; upCast 5.0.1]
          - Added empty element generation for elements that require
            at least one child (chapter, section, footnote, ... ) to increase
            rate of validation
          - several bugfixes and changes regarding section nesting
          - bug fixed with inline images, now exported as inlinemediaobject
          - fix for reliable detection of elements in $tagclasses
          - improved conversion of content models not immediately compatible
            between upCast DTD and DocBook DTD (e.g. superscript/subscript +
            footnote, placement of annotations)
          - Added support for endnote element by mapping it onto footnote
          
     1.2  [2004-07-15; upCast 5.1.1]
          - add central <para> writing template
          - add parameter omitDefaultRole
  
     1.3  [2004-09-14]
          - improved handling of images with captions
          - improved handling of tables with captions
          
     1.4  [2004-09-20]
     	  - add empty para element to otherwise empty <listitem> elements to
     	    create valid document
     	    
     1.5  [2005-02-19]
          - add heuristics to determine entry's align attribute value. If it cannot be determined reliably,
            the attribute is not written at all.
            
     1.6  [2005-03-22]
          - add parameter "inlines.graphical": When true, manual formattings for bold and italic are 
            converted to <emphasis role="bold"/> and <emphasis />, respectively. The default is 'true'. 
  
     ***************************************************
-->

<!-- Parameterization. -->

<!-- Parameters generated from the caller -->
<xsl:param name="encoding">UTF-8</xsl:param>
<xsl:param name="rootElement">article</xsl:param>
<xsl:param name="revisionmarkup">0</xsl:param>

<!-- customizable parameters and settable via the UI by the user -->
<!-- BLOCKS -->
<xsl:param name="blockquote">blockquote quote blockQuote BlockQuote Zitat</xsl:param>
<xsl:param name="address">address Adresse</xsl:param>
<xsl:param name="literallayout">literallayout literal</xsl:param>
<xsl:param name="programlisting">listing programlisting</xsl:param>
<xsl:param name="screen">screen</xsl:param>
<xsl:param name="epigraph">epigraph</xsl:param>
<xsl:param name="highlights">highlights Highlight</xsl:param>
<xsl:param name="tip">tip Tipp</xsl:param>
<xsl:param name="note">Note note Hinweis</xsl:param>
<xsl:param name="caution">caution Caution Achtung Sicherheitshinweis</xsl:param>
<xsl:param name="important">Important important wichtig Wichtig!</xsl:param>
<xsl:param name="warning">Warnung warning</xsl:param>
<xsl:param name="caption">caption figurecaption Beschriftung</xsl:param>

<!-- INLINES -->
<xsl:param name="tagclasses">'personname' 'abbrev' 'acronym' 'emphasis' 'quote' 'trademark' 'citation' 'citetitle'
   'firstterm' 'glossterm' 'foreignphrase' 'wordasword' 'computeroutput' 'literal' 'markup' 'prompt' 'replaceable' 
   'sgmltag' 'userinput' 'subscript' 'superscript' 'accel' 'guibutton' 'guiicon' 'guilabel' 'guimenu' 'guimenuitem'
   'guisubmenu' 'keycap' 'keycode' 'keysym' 'mousebutton' 'action' 'classname' 'constant' 'errorcode' 'errorname'
   'errortype function' 'interface' 'msgtext' 'parameter' 'property' 'returnvalue' 'structfield' 'structname'
   'symbol' 'token' 'type' 'varname' 'application' 'command' 'envar' 'filename' 'medialabel' 'option' 'systemitem'
   'database' 'email' 'hardware' 'medialabel' 'optional'</xsl:param>

<!-- Handling of Generated Text -->
<xsl:param name="usePIsForGentext">false</xsl:param>

<!-- Other options -->
<xsl:param name="omitDefaultRole">true</xsl:param> <!-- if true, supresses writing role attributes when it is 'Normal' on <para>graphs and 'Default Paragraph Font' on <inline>s -->
<xsl:param name="inlines.graphical">true</xsl:param>


<!-- internal parameters -->
<xsl:param name="excludeInAnnotation">table list par</xsl:param>
<xsl:param name="ucBlockElements">document part section heading par list item footnote endnote table row cell entry tgroup index toc annotation</xsl:param>
<xsl:param name="subdocelements">'header' 'footer' 'footnote' 'endnote' 'annotation' 'subdoc' 'textbox'</xsl:param>
<xsl:param name="mathmlnamespace">http://www.w3.org/1998/Math/MathML</xsl:param>

<!-- Output method: XML -->
<xsl:output method="xml" 
            indent="no"
            encoding="UTF-8"
            doctype-public="-//OASIS//DTD DocBook XML V4.2//EN"
            doctype-system="http://www.oasis-open.org/docbook/xml/4.2/docbookx.dtd"
            omit-xml-declaration="no"
/>

<!-- ####################
  DOCUMENT element
     ####################-->
<xsl:template match="document">
  <xsl:if test="$encoding != 'UTF-8'">
    <xsl:message>Error: You cannot set a different encoding than 'UTF-8' via parameter. To change the encoding, edit the db42.xsl stylesheet.</xsl:message>
  </xsl:if>
    
  <xsl:choose>

    <!-- book -->
    <xsl:when test="$rootElement='book'">
      <book>
        <xsl:if test="@xml:lang">
          <xsl:attribute name="lang">
            <xsl:value-of select="attribute::xml:lang" />
          </xsl:attribute>
        </xsl:if>
        <title><xsl:value-of select="documentinfo/property[@name='title']/@value" /></title>
        <xsl:apply-templates />
      </book>
    </xsl:when>

    <!-- article -->
    <xsl:when test="$rootElement='article'">
      <article>
        <xsl:if test="@xml:lang">
          <xsl:attribute name="lang">
            <xsl:value-of select="attribute::xml:lang" />
          </xsl:attribute>
        </xsl:if>
        <title><xsl:value-of select="documentinfo/property[@name='title']/@value" /></title>
        <xsl:apply-templates />
      </article>
    </xsl:when>

    <!-- custom -->
    <xsl:when test="$rootElement='custom'">
      <article>
        <title>Custom structure</title>
        <para>You need to modify the stylesheet appropriately to generate DocBook output with custom structure.</para>
      </article>
    </xsl:when>
  </xsl:choose>
  <xsl:comment> Document conversion from RTF by upCast <xsl:value-of select="my-class:getVersion()" />, (c) 1999-2004 infinity-loop &lt;www.infinity-loop.de&gt; </xsl:comment>
</xsl:template>


<!-- ####################
  DOCUMENTINFO element
     ####################-->
<xsl:template match="documentinfo">
  <xsl:element name="{$rootElement}info">
    <xsl:if test="count(property[@name='title']) = 0">
      <title>Untitled Document</title>
    </xsl:if>
    <xsl:apply-templates />
  </xsl:element>
</xsl:template>


<!-- ####################
  PROPERTY element
     ####################-->
<xsl:template match="property[@name != 'title']">
  <xsl:choose>
  
    <!-- author -->
    <xsl:when test="@name='author'">
      <xsl:if test="normalize-space(@value)">
        <author>
          <xsl:copy-of select="my-class:parsePersonname(@value)" />
        </author>
      </xsl:if>
    </xsl:when>

    <!-- operator -->
    <xsl:when test="@name='operator'">
      <xsl:if test="normalize-space(@value)">
        <othercredit role="operator">
          <xsl:copy-of select="my-class:parsePersonname(@value)" />
        </othercredit>
      </xsl:if>
    </xsl:when>

    <!-- creationTime -->
    <xsl:when test="@name='creationTime'">
      <date><xsl:value-of select="@value" /></date>
    </xsl:when>

    <!-- editingMinutes -->
    <xsl:when test="@name='editingMinutes'">
      <xsl:comment><xsl:text>Editing minutes: </xsl:text><xsl:value-of select="@value" /></xsl:comment>
    </xsl:when>

    <!-- numberOfPages -->
    <xsl:when test="@name='numberOfPages'">
      <xsl:comment><xsl:text>Original number of pages: </xsl:text><xsl:value-of select="@value" /></xsl:comment>
    </xsl:when>

    <!-- numberOfWords -->
    <xsl:when test="@name='numberOfWords'">
      <xsl:comment><xsl:text>Original number of words: </xsl:text><xsl:value-of select="@value" /></xsl:comment>
    </xsl:when>

    <!-- numberOfChars -->
    <xsl:when test="@name='numberOfChars'">
      <xsl:comment><xsl:text>Original number of characters: </xsl:text><xsl:value-of select="@value" /></xsl:comment>
    </xsl:when>

    <!-- numberOfCharsWS -->
    <xsl:when test="@name='numberOfCharsWS'">
      <xsl:comment><xsl:text>Original number of characters (incl. whitespace): </xsl:text><xsl:value-of select="@value" /></xsl:comment>
    </xsl:when>

    <!-- revisionTime -->
    <xsl:when test="@name='revisionTime'">
      <revhistory>
        <revision>
          <revnumber><xsl:text>1</xsl:text></revnumber>
          <date><xsl:value-of select="@value" /></date>
        </revision>
      </revhistory>
    </xsl:when>

    <!-- lastPrintTime -->
    <xsl:when test="@name='lastPrintTime'">
      <printhistory>
        <para><xsl:text>Last printed: </xsl:text><xsl:value-of select="@value"/></para>
      </printhistory>
    </xsl:when>

    <!-- company -->
    <xsl:when test="@name='company'">
      <corpname><xsl:value-of select="@value" /></corpname>
    </xsl:when>

    <!-- ..unknown.. : write as comment -->
    <xsl:otherwise>
      <xsl:comment><xsl:value-of select="@name" /><xsl:text>: </xsl:text><xsl:value-of select="@value" /></xsl:comment>
    </xsl:otherwise>

  </xsl:choose>
</xsl:template>


<!-- ####################
  PART element
     ####################-->
<xsl:template name="part2">
  <xsl:param name="writeTitle">true</xsl:param>
  <xsl:choose>
    <xsl:when test="$rootElement='book'">  
      <xsl:choose>
        
        <!-- only sections: good! -->
        <xsl:when test="count(./section) = count(./*)">
          <xsl:if test="@page-break-before='always' and (current()[position() > 1])">
            <beginpage />
          </xsl:if>
          <xsl:if test="$writeTitle = 'true'">
            <title><xsl:value-of select="section[1]/heading" /></title>
          </xsl:if>
          <xsl:apply-templates />
        </xsl:when>
        
        <!-- there are also other nodes at the start: group them into a preface element -->
        <xsl:when test="(count(./section) &lt; count(./*)) and (count(./section) &gt; 0)">
          <xsl:if test="@page-break-before='always' and (current()[position() > 1])">
            <beginpage />
          </xsl:if>
          <xsl:if test="$writeTitle = 'true'">
            <title><xsl:value-of select="section/heading" /></title>
          </xsl:if>
          <preface>
            <title />
            <xsl:apply-templates select="./*[name() != 'section']" />
          </preface>
          <xsl:apply-templates select="section" />
        </xsl:when>

        <xsl:otherwise>
          <xsl:if test="$writeTitle = 'true'">
            <title>Untitled Part</title>
          </xsl:if>
          <chapter>
            <title>Untitled Chapter</title>
            <xsl:message>missing chapter title</xsl:message>
            <xsl:apply-templates />
          </chapter>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:when>
    
    <xsl:when test="$rootElement='article'">
      <xsl:choose>
        
        <!-- only sections: good! -->
        <xsl:when test="count(./section) = count(./*)">
          <xsl:if test="@page-break-before='always' and (current()[position() > 1])">
            <beginpage />
          </xsl:if>
          <xsl:if test="$writeTitle = 'true'">
            <title>Untitled Section</title>
          </xsl:if>
          <xsl:apply-templates />
        </xsl:when>
        
        <!-- there are also other nodes at the start -->
        <xsl:when test="(count(./section) &lt; count(./*)) and (count(./section) &gt; 0)">
          <xsl:if test="@page-break-before='always' and (current()[position() > 1])">
            <beginpage />
          </xsl:if>
          <xsl:if test="$writeTitle = 'true'">
            <title>Untitled Section</title>
          </xsl:if>
          <xsl:apply-templates select="./*[name() != 'section']" />
          <xsl:apply-templates select="section" />
        </xsl:when>

        <xsl:otherwise>
          <xsl:if test="$writeTitle = 'true'">
            <title>Untitled Section</title>
          </xsl:if>
          <xsl:apply-templates />
        </xsl:otherwise>
      </xsl:choose>
    </xsl:when>
  </xsl:choose>
</xsl:template>

<xsl:template match="part">
  <xsl:choose>

    <!-- book -->
    <xsl:when test="$rootElement='book'">
      <xsl:choose>

        <!-- we skip the part element if there is only one part -->    
        <xsl:when test="count(preceding-sibling::part) = 0 and count(following-sibling::part) = 0">
          <xsl:call-template name="part2">
            <xsl:with-param name="writeTitle">false</xsl:with-param>
          </xsl:call-template>
        </xsl:when>
        
        <xsl:otherwise>
          <part>
            <xsl:call-template name="part2">
              <xsl:with-param name="writeTitle">true</xsl:with-param>
            </xsl:call-template>
          </part>
        </xsl:otherwise>

      </xsl:choose>
    </xsl:when>
    
    <!-- article: We make parts into sections with an empty title, except for when there's only one single part.  -->
    <xsl:when test="$rootElement='article'">
      <xsl:choose>

        <!-- we skip the part element if there is only one part -->    
        <xsl:when test="count(preceding-sibling::part) = 0 and count(following-sibling::part) = 0">
          <xsl:call-template name="part2">
            <xsl:with-param name="writeTitle">false</xsl:with-param>
          </xsl:call-template>
        </xsl:when>
        
        <xsl:otherwise>
          <section>
            <xsl:call-template name="part2">
              <xsl:with-param name="writeTitle">true</xsl:with-param>
            </xsl:call-template>
          </section>
        </xsl:otherwise>

      </xsl:choose>
    </xsl:when>

  </xsl:choose>
</xsl:template>


<!-- ####################
  SECTION elements
     ####################-->
<xsl:template match="section[@level='1']">
  <xsl:choose>
    <xsl:when test="$rootElement='book'">
      <chapter>
        <title><xsl:apply-templates select="heading" /></title>
        <xsl:apply-templates select=".//textbox[ancestor::section[1] = current()]" mode="partextbox" />
        <xsl:choose>
          <xsl:when test="count( *[name() != 'heading'] ) > 0">
            <xsl:apply-templates select="*[name() != 'heading']" />
          </xsl:when>
          <xsl:otherwise>
            <!-- write empty paragraph to be valid -->
            <para></para>
          </xsl:otherwise>
        </xsl:choose>
      </chapter>
    </xsl:when>
    <xsl:when test="$rootElement='article'">
      <section>
        <title><xsl:apply-templates select="heading" /></title>
        <xsl:apply-templates select=".//textbox[ancestor::section[1] = current()]" mode="partextbox" />
        <xsl:choose>
          <xsl:when test="count( *[name() != 'heading'] ) > 0">
            <xsl:apply-templates select="*[name() != 'heading']" />
          </xsl:when>
          <xsl:otherwise>
            <!-- write empty paragraph to be valid -->
            <para></para>
          </xsl:otherwise>
        </xsl:choose>
      </section>
    </xsl:when>
  </xsl:choose>    
</xsl:template>

<xsl:template match="section[@level!='1']">
  <section>
    <title><xsl:apply-templates select="heading" /></title>
    <xsl:apply-templates select=".//textbox[ancestor::section[1] = current()]" mode="partextbox" />
    <xsl:choose>
      <xsl:when test="count( *[name() != 'heading'] ) > 0">
        <xsl:apply-templates select="*[name() != 'heading']" />
      </xsl:when>
      <xsl:otherwise>
        <para></para>
      </xsl:otherwise>
    </xsl:choose>
  </section>
</xsl:template>


<!-- ####################
  HEADING element
  .. Note: does not generate surrounding <title> tag!
     ####################-->
<xsl:template match="heading">
  <xsl:apply-templates />
</xsl:template>


<!-- ####################
  PAR element
     ####################-->
<!-- helpers: determine possible grouper. -->
<xsl:template name="determineParGrouper">
  <xsl:param name="role" />
  <xsl:choose>
    <xsl:when test="contains( $blockquote, $role )">blockquote</xsl:when>
    <xsl:when test="contains( $address, $role )">address</xsl:when>
    <xsl:when test="contains( $literallayout, $role )">literallayout</xsl:when>
    <xsl:when test="contains( $programlisting, $role )">programlisting</xsl:when>
    <xsl:when test="contains( $screen, $role )">screen</xsl:when>
    <xsl:when test="contains( $epigraph, $role )">epigraph</xsl:when>
    <xsl:when test="contains( $highlights, $role )">highlights</xsl:when>
    <xsl:when test="contains( $caution, $role )">caution</xsl:when>
    <xsl:when test="contains( $important, $role )">important</xsl:when>
    <xsl:when test="contains( $note, $role )">note</xsl:when>
    <xsl:when test="contains( $tip, $role )">tip</xsl:when>
    <xsl:when test="contains( $warning, $role )">warning</xsl:when>
  </xsl:choose>
</xsl:template>


<xsl:template match="par">
  <xsl:param name="doPars">false</xsl:param>

  <xsl:if test="@page-break-before = 'always'">
    <xsl:call-template name="pagebreak" />
  </xsl:if>
  
  <!-- we need to exclude captions -->
  <xsl:choose>

    <xsl:when test="($doPars = 'false') and contains($caption, @class) and 
                    ( count( preceding-sibling::*[position()=1 and name()='par' and count(descendant::image)>0] )=1
                      or count( preceding-sibling::*[position()=1 and name()='table'] )=1
                      or
                      count( following-sibling::*[position()=1 and name()='par' and count(descendant::image)>0] )=1
                      or count( following-sibling::*[position()=1 and name()='table'] )=1
                    )" >

<!--
    <xsl:when test="contains($caption, @class) and 
                    (    preceding-sibling::*[position()=1 and name()='image'] 
                      or following-sibling::*[position()=1 and name()='image']
                    )" >
-->                    
      <!-- skip! -->
    </xsl:when>
  
    <xsl:otherwise>
      
      <!-- We want to support grouping of adjacent paragraphs with matching role by an according element.
           Therefore, we need to determine some things first, which we store in a variable. -->
    
      <xsl:variable name="groupBy">
        <xsl:call-template name="determineParGrouper">
          <xsl:with-param name="role"><xsl:value-of select="@class" /></xsl:with-param>
        </xsl:call-template>
      </xsl:variable>
      
      <xsl:choose>
      
        <!-- grouper specified -->
        <xsl:when test="$groupBy != ''">
          
          <xsl:choose>
            <xsl:when test="$groupBy='literallayout'"> <!-- need to strip par's -->
              <xsl:choose>
                <xsl:when test="(count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class])">

                  <!-- This is the first par of a possibly contiguous sequence of paragraphs to be grouped by groupBy element. -->
                  <xsl:variable name="pos" select="1 + count(preceding-sibling::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))])" />
                  <xsl:element name="{$groupBy}">
                    <xsl:apply-templates />
                    <xsl:apply-templates select="following-sibling::par[(@class=current()/@class) and (count(self::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))]) + count(preceding-sibling::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))])) = $pos]">
                      <xsl:with-param name="doPars">true</xsl:with-param>
                    </xsl:apply-templates>
                  </xsl:element>
                </xsl:when>

                <xsl:otherwise>
                  <!-- This is NOT a first par in a grouping row. Handle them normally. -->
                  <xsl:if test="$doPars = 'true'">
                    <xsl:apply-templates />
                  </xsl:if>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:when>
            <xsl:when test="$groupBy ='programlisting'">
                <xsl:choose>
                    <xsl:when test="(count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class])">
                        <!-- This is the first par of a possibly contiguous sequence of paragraphs to be grouped by groupBy element. -->
                        <xsl:variable name="pos" select="1 + count(preceding-sibling::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))])"/>
                            <xsl:element name="{$groupBy}">
                                <xsl:apply-templates select=".//textbox" mode="partextbox"/>
                                <xsl:choose>
                                    <xsl:when test="(count(following-sibling::*) = 0) or (following-sibling::*[1][name()!='par' or @class!=current()/@class])">
                                        <xsl:call-template name="paraprogramlisting"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:call-template name="paraprogramlisting_linebreak"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <xsl:apply-templates select="following-sibling::par[(@class=current()/@class) and (count(self::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))]) + count(preceding-sibling::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))])) = $pos]">
                                    <xsl:with-param name="doPars">true</xsl:with-param>
                                </xsl:apply-templates>
                             </xsl:element>
                          </xsl:when>
                          <xsl:otherwise>
                            <!-- This is NOT a first par in a grouping row. Handle them normally. -->
                            <xsl:if test="$doPars = 'true'">
                                <xsl:apply-templates select=".//textbox" mode="partextbox"/>
                                <xsl:choose>
                                    <xsl:when test="(count(following-sibling::*) = 0) or (following-sibling::*[1][name()!='par' or @class!=current()/@class])">
                                        <xsl:call-template name="paraprogramlisting"/>
                                    </xsl:when>
<!--
with UpCast5.3.2 no extra linebreak is needed, don't know why
-->
                                    <xsl:otherwise>
                                        <xsl:call-template name="paraprogramlisting_linebreak"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:if>
                        </xsl:otherwise>
                    </xsl:choose>
                 </xsl:when>
                 
            <!-- generic handling -->
            <xsl:otherwise>
              <xsl:choose>
                <xsl:when test="(count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class])">

                  <!-- This is the first par of a possibly contiguous sequence of paragraphs to be grouped by groupBy element. -->
                  <xsl:variable name="pos" select="1 + count(preceding-sibling::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))])" />
                  <xsl:element name="{$groupBy}">
                    <xsl:apply-templates select=".//textbox" mode="partextbox"/>
                    <xsl:call-template name="para" />
                    <xsl:apply-templates select="following-sibling::par[(@class=current()/@class) and (count(self::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))]) + count(preceding-sibling::par[(@class=current()/@class) and ((count(preceding-sibling::*) = 0) or (preceding-sibling::*[1][name()!='par' or @class!=current()/@class]))])) = $pos]">
                      <xsl:with-param name="doPars">true</xsl:with-param>
                    </xsl:apply-templates>
                  </xsl:element>
                </xsl:when>

                <xsl:otherwise>
                  <!-- This is NOT a first par in a grouping row. Handle them normally. -->
                  <xsl:if test="$doPars = 'true'">
                    <xsl:apply-templates select=".//textbox" mode="partextbox" />
                    <xsl:call-template name="para" />
                  </xsl:if>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:otherwise>

          </xsl:choose>
          
        </xsl:when>
        
        <!-- no grouping -->
        <xsl:otherwise>
          <xsl:apply-templates select=".//textbox" mode="partextbox" />
          <xsl:call-template name="para" />
        </xsl:otherwise>

      </xsl:choose>
    </xsl:otherwise>
  </xsl:choose>

  <!-- pull out after: INDEX -->
  <xsl:if test="count(.//gentext[@type='INDEX']) > 0">
    <index><xsl:comment> Index is to be generated here by processing system </xsl:comment></index>
  </xsl:if>


</xsl:template>

<xsl:template name="paraprogramlisting">
  <xsl:param name="role" select="@class"/>
  <xsl:if test="($omitDefaultRole = 'false') or ($role != 'Normal')">
    <xsl:attribute name="role">
      <xsl:value-of select="$role"/>
    </xsl:attribute>
  </xsl:if>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template name="paraprogramlisting_linebreak">
  <xsl:param name="role" select="@class"/>
  <xsl:if test="($omitDefaultRole = 'false') or ($role != 'Normal')">
    <xsl:attribute name="role">
      <xsl:value-of select="$role"/>
    </xsl:attribute>
  </xsl:if>
  <xsl:apply-templates/>
<!--  <xsl:text disable-output-escaping="yes">&lt;?d-linebreak?&gt;</xsl:text>
-->
  <xsl:text>&#xa;</xsl:text>
  <!--<xsl:text disable-output-escaping="yes">&lt;?e-linebreak?&gt;</xsl:text>
  -->
</xsl:template>

<xsl:template name="para">
  <xsl:param name="role" select="@class" />
  <para>
    <xsl:if test="($omitDefaultRole = 'false') or ($role != 'Normal')">
      <xsl:attribute name="role"><xsl:value-of select="$role" /></xsl:attribute>
    </xsl:if>
    <xsl:apply-templates />
  </para>
</xsl:template>


<!-- ####################
  INLINE element
     ####################-->
<xsl:template match="inline">
  <xsl:variable name="isElem"><xsl:value-of select="contains( $tagclasses, concat( &quot;&apos;&quot;, @class, &quot;&apos;&quot;) ) and @class!=''" /></xsl:variable>

  <xsl:choose>

    <xsl:when test="@class='personname'">
      <xsl:variable name="name" select="descendant::text()" />
      <xsl:copy-of select="my-class:parsePersonname($name)" />
    </xsl:when>

    <xsl:when test="$isElem='true'">
      <xsl:element name="{@class}">
        <xsl:if test="@xml:lang">
          <xsl:attribute name="lang"><xsl:value-of select="@xml:lang" /></xsl:attribute>
        </xsl:if>
        <xsl:apply-templates />
      </xsl:element>
    </xsl:when>
    
    <!-- sub, super -->
    <xsl:when test="(@vertical-align!='baseline' and @vertical-align!='sub') and (not( starts-with( @vertical-align, '-' )) or @vertical-align='super')">
      <superscript>
        <xsl:if test="@xml:lang">
          <xsl:attribute name="lang"><xsl:value-of select="@xml:lang" /></xsl:attribute>
        </xsl:if>
        <xsl:apply-templates select="child::node()[name() != 'footnote' and name() != 'endnote' and name() != 'image']"/> <!-- no footnotes, endnotes, images in superscript elements! -->
      </superscript>
      <xsl:apply-templates select="child::footnote|child::endnote|child::image" />
    </xsl:when>
    <xsl:when test="starts-with( @vertical-align, '-' ) or (@vertical-align='sub')">
      <subscript>
        <xsl:if test="@xml:lang">
          <xsl:attribute name="lang"><xsl:value-of select="@xml:lang" /></xsl:attribute>
        </xsl:if>
        <xsl:apply-templates select="child::node()[name() != 'footnote' and name() != 'endnote' and name() != 'image']"/> <!-- see above -->
      </subscript>
      <xsl:apply-templates select="child::footnote|child::endnote|child::image" />
    </xsl:when>

      <xsl:when test="@font-style='italic' and $inlines.graphical = 'true'">
        <emphasis>
          <xsl:if test="@xml:lang">
            <xsl:attribute name="lang"><xsl:value-of select="@xml:lang" /></xsl:attribute>
          </xsl:if>
          <xsl:apply-templates />
        </emphasis>
      </xsl:when>
      <xsl:when test="@font-weight='bold' and $inlines.graphical = 'true'">
        <emphasis role="bold">
          <xsl:if test="@xml:lang">
            <xsl:attribute name="lang"><xsl:value-of select="@xml:lang" /></xsl:attribute>
          </xsl:if>
          <xsl:apply-templates />
        </emphasis>
      </xsl:when>
    
    <xsl:otherwise>
      <xsl:choose>
        <xsl:when test="@class or @xml:lang">
          <phrase>
            <xsl:if test="@class">
              <xsl:if test="($omitDefaultRole = 'false') or (@class != 'Default Paragraph Font')">
                <xsl:attribute name="role"><xsl:value-of select="@class"/></xsl:attribute>
              </xsl:if>
            </xsl:if>
            <xsl:if test="@xml:lang">
              <xsl:attribute name="lang"><xsl:value-of select="@xml-lang" /></xsl:attribute>
            </xsl:if>
            <xsl:apply-templates />
          </phrase>
        </xsl:when>
        <xsl:otherwise>
          <xsl:apply-templates />
        </xsl:otherwise>
      </xsl:choose>
    </xsl:otherwise>

  </xsl:choose>
</xsl:template>

<xsl:template match="inline" mode="hidden">
  <xsl:apply-templates mode="hidden" />
</xsl:template>


<!-- ####################
  HIDDEN element
     ####################-->
<xsl:template match="hidden">
  <xsl:apply-templates mode="hidden" />
</xsl:template>


<!-- ####################
  LIST element
     ####################-->
<xsl:template match="list">
  <xsl:variable name="lt"><xsl:value-of select="@list-style-type"/></xsl:variable>
    <!-- JS to have more control about the mark symbol -->
  <xsl:variable name="markerformat"><xsl:value-of select="@ilx-marker-format"/></xsl:variable>

  <xsl:choose>
    <xsl:when test="($lt='disc') or ($lt='square') or ($lt='circle') or ($lt='hyphen')">
      <itemizedlist>    
     <xsl:choose>
     <xsl:when test="($markerformat='o')">
      <xsl:attribute name="mark">opencircle</xsl:attribute>
     </xsl:when>
     <xsl:when test="($markerformat='\2212 ')">
      <xsl:attribute name="mark">box</xsl:attribute>
     </xsl:when>
     <xsl:otherwise>
      <xsl:attribute name="mark"><xsl:copy-of select="$lt" /></xsl:attribute>
     </xsl:otherwise>
     </xsl:choose>
        <xsl:attribute name="spacing">
          <xsl:choose>
            <xsl:when test="starts-with(@ilx-marker-offset, '-')"> <!-- negative indent -> normal list! -->
              <xsl:text>normal</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>compact</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:attribute>
        <xsl:apply-templates />
      </itemizedlist>
    </xsl:when>
    
    <xsl:when test="($lt='lower-roman')">
      <orderedlist numeration="lowerroman">
        <xsl:if test="@startat > 1">
          <xsl:attribute name="continuation">continues</xsl:attribute>
        </xsl:if>
        <xsl:attribute name="spacing">
          <xsl:choose>
            <xsl:when test="starts-with(@ilx-marker-offset, '-')"> <!-- negative indent -> normal list! -->
              <xsl:text>normal</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>compact</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:attribute>
        <xsl:if test="string-length( substring-after( substring-after(@ilx-marker-format, '%'), '%')) > 0"> <!-- at least two 2 '%'s -->
          <xsl:attribute name="inheritnum">inherit</xsl:attribute>
        </xsl:if>
        <xsl:apply-templates />
      </orderedlist>
    </xsl:when>

    <xsl:when test="($lt='lower-alpha') or ($lt='lower-greek')">
      <orderedlist numeration="loweralpha">
        <xsl:if test="@startat > 1">
          <xsl:attribute name="continuation">continues</xsl:attribute>
        </xsl:if>
        <xsl:attribute name="spacing">
          <xsl:choose>
            <xsl:when test="starts-with(@ilx-marker-offset, '-')"> <!-- negative indent -> normal list! -->
              <xsl:text>normal</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>compact</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:attribute>
        <xsl:if test="string-length( substring-after( substring-after(@ilx-marker-format, '%'), '%')) > 0"> <!-- at least two 2 '%'s -->
          <xsl:attribute name="inheritnum">inherit</xsl:attribute>
        </xsl:if>
        <xsl:apply-templates />
      </orderedlist>
    </xsl:when>
    
    <xsl:when test="($lt='upper-alpha')">
      <orderedlist numeration="upperalpha">
        <xsl:if test="@startat > 1">
          <xsl:attribute name="continuation">continues</xsl:attribute>
        </xsl:if>
        <xsl:attribute name="spacing">
          <xsl:choose>
            <xsl:when test="starts-with(@ilx-marker-offset, '-')"> <!-- negative indent -> normal list! -->
              <xsl:text>normal</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>compact</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:attribute>
        <xsl:if test="string-length( substring-after( substring-after(@ilx-marker-format, '%'), '%')) > 0"> <!-- at least two 2 '%'s -->
          <xsl:attribute name="inheritnum">inherit</xsl:attribute>
        </xsl:if>
        <xsl:apply-templates />
      </orderedlist>
    </xsl:when>

    <xsl:when test="($lt='upper-roman')">
      <orderedlist numeration="upperroman">
        <xsl:if test="@startat > 1">
          <xsl:attribute name="continuation">continues</xsl:attribute>
        </xsl:if>
        <xsl:attribute name="spacing">
          <xsl:choose>
            <xsl:when test="starts-with(@ilx-marker-offset, '-')"> <!-- negative indent -> normal list! -->
              <xsl:text>normal</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>compact</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:attribute>
        <xsl:if test="string-length( substring-after( substring-after(@ilx-marker-format, '%'), '%')) > 0"> <!-- at least two 2 '%'s -->
          <xsl:attribute name="inheritnum">inherit</xsl:attribute>
        </xsl:if>
        <xsl:apply-templates />
      </orderedlist>
    </xsl:when>
    
    <xsl:otherwise>
      <orderedlist numeration="arabic">
        <xsl:if test="@startat > 1">
          <xsl:attribute name="continuation">continues</xsl:attribute>
        </xsl:if>
        <xsl:attribute name="spacing">
          <xsl:choose>
            <xsl:when test="starts-with(@ilx-marker-offset, '-')"> <!-- negative indent -> normal list! -->
              <xsl:text>normal</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>compact</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:attribute>
        <xsl:if test="string-length( substring-after( substring-after(@ilx-marker-format, '%'), '%')) > 0"> <!-- at least two 2 '%'s -->
          <xsl:attribute name="inheritnum">inherit</xsl:attribute>
        </xsl:if>
        <xsl:apply-templates />
      </orderedlist>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>


<!-- ####################
  ITEM element
     ####################-->
<xsl:template match="item">
  <listitem>
  	<xsl:choose>
  		<xsl:when test="count( child::* ) = 0">
  			<para /> <!-- we need to create at least an empty para to make the document valid. -->
  		</xsl:when>
  		<xsl:otherwise>
		    <xsl:apply-templates />
  		</xsl:otherwise>
  	</xsl:choose>
  </listitem>
</xsl:template>


<!-- ####################
  IMAGE element
     ####################-->
<xsl:template match="image">
  <xsl:variable name="captiontext">
    <caption>
    <xsl:choose>
<!--      
        <xsl:when test="count(following-sibling::*[position()=1 and name()='par' and contains($caption, @class)]) > 0">
          <xsl:apply-templates select="following-sibling::*[position()=1 and name()='par' and contains($caption, @class)]" />
        </xsl:when>
        <xsl:when test="count(preceding-sibling::*[position()=1 and name()='par' and contains($caption, @class)]) > 0">
          <xsl:apply-templates select="preceding-sibling::*[position()=1 and name()='par' and contains($caption, @class)]" />
        </xsl:when>
-->
        <!-- First, check for caption AFTER the image -->
        <xsl:when test="count(ancestor::par[1]/following-sibling::*[position()=1 and name()='par' and contains($caption, @class)]) > 0
                        and count(ancestor::par[1]/descendant::image) = 1">
          <xsl:apply-templates select="ancestor::par[1]/following-sibling::*[position()=1 and name()='par' and contains($caption, @class)]">
            <xsl:with-param name="doPars">true</xsl:with-param>
          </xsl:apply-templates>
        </xsl:when>
        <!-- ...and only then before. -->
        <xsl:when test="count(ancestor::par[1]/preceding-sibling::*[position()=1 and name()='par' and contains($caption, @class)]) > 0
                        and count(ancestor::par[1]/descendant::image) = 1">
          <xsl:apply-templates select="ancestor::par[1]/preceding-sibling::*[position()=1 and name()='par' and contains($caption, @class)]">
            <xsl:with-param name="doPars">true</xsl:with-param>
          </xsl:apply-templates>
        </xsl:when>
    </xsl:choose>
    </caption>
  </xsl:variable>
  
  <xsl:variable name="objname">
    <xsl:choose>
      <xsl:when test="string-length( string($captiontext) ) &gt; 0">mediaobject</xsl:when>
      <xsl:otherwise>inlinemediaobject</xsl:otherwise>
    </xsl:choose>
  </xsl:variable>

  <xsl:choose>
    <xsl:when test="$objname = 'mediaobject'">
  <mediaobject>
    <imageobject>
      <xsl:element name="imagedata">
        <xsl:choose>
          <xsl:when test="@width">
            <xsl:attribute name="width"><xsl:value-of select="@width" /></xsl:attribute>
          </xsl:when>
          <xsl:when test="@width_px">
            <xsl:attribute name="width"><xsl:value-of select="@width_px" /><xsl:text>px</xsl:text></xsl:attribute>
          </xsl:when>
        </xsl:choose>
        <xsl:choose>
          <xsl:when test="@height">
            <xsl:attribute name="depth"><xsl:value-of select="@height" /></xsl:attribute>
          </xsl:when>
          <xsl:when test="@height_px">
            <xsl:attribute name="depth"><xsl:value-of select="@height_px" /><xsl:text>px</xsl:text></xsl:attribute>
          </xsl:when>
        </xsl:choose>
        <xsl:attribute name="fileref"><xsl:value-of select="@xlink:href" /></xsl:attribute>
        <xsl:attribute name="format"><xsl:value-of select="my-class:determineImageFormat(@xlink:href)" /></xsl:attribute>
        <xsl:attribute name="srccredit"><xsl:value-of select="@imagesource" /></xsl:attribute>
      </xsl:element>
    </imageobject>
    <xsl:if test="@description">
      <textobject>
        <para><xsl:value-of select="@description" /></para>
      </textobject>
    </xsl:if>
    <xsl:copy-of select="$captiontext" />
  </mediaobject>
    </xsl:when>
    <xsl:otherwise>
  <inlinemediaobject>
    <imageobject>
      <xsl:element name="imagedata">
        <xsl:choose>
          <xsl:when test="@width">
            <xsl:attribute name="width"><xsl:value-of select="@width" /></xsl:attribute>
          </xsl:when>
          <xsl:when test="@width_px">
            <xsl:attribute name="width"><xsl:value-of select="@width_px" /><xsl:text>px</xsl:text></xsl:attribute>
          </xsl:when>
        </xsl:choose>
        <xsl:choose>
          <xsl:when test="@height">
            <xsl:attribute name="depth"><xsl:value-of select="@height" /></xsl:attribute>
          </xsl:when>
          <xsl:when test="@height_px">
            <xsl:attribute name="depth"><xsl:value-of select="@height_px" /><xsl:text>px</xsl:text></xsl:attribute>
          </xsl:when>
        </xsl:choose>
        <xsl:attribute name="fileref"><xsl:value-of select="@xlink:href" /></xsl:attribute>
        <xsl:attribute name="format"><xsl:value-of select="my-class:determineImageFormat(@xlink:href)" /></xsl:attribute>
        <xsl:attribute name="srccredit"><xsl:value-of select="@imagesource" /></xsl:attribute>
      </xsl:element>
    </imageobject>
    <xsl:if test="@description">
      <textobject>
        <para><xsl:value-of select="@description" /></para>
      </textobject>
    </xsl:if>
  </inlinemediaobject>
    </xsl:otherwise>
  </xsl:choose>
  
</xsl:template>


<!-- ####################
  SUBDOC element
     ####################-->
<xsl:template match="subdoc">
  <xsl:message>unexpected subdoc element found - report to &gt;support@infinity-loop.de&lt;</xsl:message>
</xsl:template>


<!-- ####################
  REFERENCE element
     ####################-->
<xsl:template match="reference">
  <xsl:choose>

    <!-- check if this is a footnote id: if it is, we need to use footnoteref instead of xref -->
    <xsl:when test="count(//footnote[my-class:makeValidID(@rawtarget)=my-class:makeValidID(current()/@rawtarget)]) > 0
                    or
                    count(//endnote[my-class:makeValidID(@rawtarget)=my-class:makeValidID(current()/@rawtarget)]) > 0">
      <footnoteref>
        <xsl:attribute name="linkend"><xsl:value-of select="my-class:makeValidID(@rawtarget)" /></xsl:attribute>
      </footnoteref>
    </xsl:when>  

    <xsl:otherwise>
      <link>
        <xsl:attribute name="linkend"><xsl:value-of select="my-class:makeValidID(@rawtarget)" /></xsl:attribute>
        <xsl:apply-templates />
      </link>
<!--  ### JS: changed on 09122005 cause of ??? in link generation
      <xref>
        <xsl:attribute name="linkend"><xsl:value-of select="my-class:makeValidID(@rawtarget)" /></xsl:attribute>
      </xref>
-->
    </xsl:otherwise>

  </xsl:choose>
</xsl:template>


<!-- ####################
  FOOTNOTE element
     ####################-->
<xsl:template match="footnote|endnote">
  <footnote>
    <xsl:if test="@id">
      <xsl:attribute name="id"><xsl:value-of select="@id" /></xsl:attribute>
    </xsl:if>
    <xsl:apply-templates />
    <xsl:if test="count( child::* ) = 0">
      <!-- write an empty para to make valid -->
      <para></para>
    </xsl:if>
  </footnote>
</xsl:template>


<!-- ####################
  TEXTBOX element
     ####################-->
<!--     
<xsl:template match="textbox[count( ancestor::par ) = 0 and count( ancestor::heading ) = 0]" >
  <sidebar>
    <xsl:apply-templates />
    <xsl:if test="count( child::node() ) = 0" >
      <para />
    </xsl:if>
  </sidebar>
</xsl:template>
-->

<xsl:template match="textbox" mode="partextbox">
  <sidebar>
    <xsl:apply-templates />
    <xsl:if test="count( child::node() ) = 0" >
      <para />
    </xsl:if>
  </sidebar>
</xsl:template>

<xsl:template match="textbox">
  <!-- exclude! -->
</xsl:template>


<!-- ####################
  ANNOTATION element
     ####################-->
<xsl:template match="annotation" name="annotation">
  <remark>
    <xsl:if test="@authorname">
      <author>
        <xsl:copy-of select="my-class:parsePersonname(@authorname)" />
      </author>
    </xsl:if>
    <xsl:if test="@authorid">
      <authorinitials>
        <xsl:value-of select="@authorid" />
      </authorinitials>
    </xsl:if>
    <xsl:apply-templates select="descendant::node()[not(contains($excludeInAnnotation, name()))]"/>
  </remark>
</xsl:template>

<!-- We need to make annotations 'visible' in hidden subtrees! -->
<xsl:template match="annotation" mode="hidden">
  <xsl:call-template name="annotation" />
</xsl:template>


<!-- ####################
  INSERTED element
     ####################-->
<xsl:template match="inserted">
  <xsl:choose>
    <xsl:when test="$revisionmarkup='1'">
      <phrase revisionflag="added">
        <xsl:apply-templates />
      </phrase>
    </xsl:when>
    <xsl:otherwise>
      <xsl:apply-templates />
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>


<!-- ####################
  DELETED element
     ####################-->
<xsl:template match="deleted">
  <xsl:choose>
    <xsl:when test="$revisionmarkup='1'">
      <phrase revisionflag="deleted">
        <xsl:apply-templates />
      </phrase>
    </xsl:when>
    <xsl:otherwise>
      <!-- content gets completely stripped from document -->
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>


<!-- ####################
  GENTEXT element
     ####################-->
<xsl:template match="gentext">
  <xsl:choose>
  
    <!-- index location -->
    <xsl:when test="(@type = 'INDEX')">
      <xsl:if test="count(ancestor::par)=0"> <!-- not in a par element -->
        <index><xsl:comment> Index is to be generated here by processing system </xsl:comment></index>
      </xsl:if>
    </xsl:when>
  
    <xsl:otherwise>
      <xsl:if test="(@type != 'upcast-FOOTNOTENUMBER') and (@type != 'upcast-HEADINGNUMBER') and (@type != 'upcast-ANNOTATIONNUMBER')">
        <xsl:choose>
          <xsl:when test="$usePIsForGentext='true'">
            <xsl:processing-instruction name="uc-gentext">
              <xsl:text>type="</xsl:text><xsl:value-of select="@type"/><xsl:text>" value="</xsl:text><xsl:value-of select="descendant::text()"/><xsl:text>"</xsl:text>
            </xsl:processing-instruction>
          </xsl:when>
          <xsl:otherwise>
            <phrase>
              <xsl:attribute name="role">
                <xsl:text>GEN_</xsl:text><xsl:value-of select="@type" />
              </xsl:attribute>
              <xsl:apply-templates />
            </phrase>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:if>
    </xsl:otherwise>
    
  </xsl:choose>
</xsl:template>


<!-- ####################
  TARGET element
     ####################-->
<xsl:template match="target">
  <anchor>
    <xsl:attribute name="id"><xsl:value-of select="my-class:makeValidID(@rawtarget)" /></xsl:attribute>
  </anchor>
</xsl:template>


<!-- ####################
  LINK element
     ####################-->
<xsl:template match="link">
  <xsl:choose>
    <xsl:when test="starts-with(@xlink:href, '#')">
      <link>
        <xsl:attribute name="linkend"><xsl:value-of select="my-class:makeValidID(@rawtarget)" /></xsl:attribute>
        <xsl:apply-templates />
      </link>
    </xsl:when>
    <xsl:otherwise>
      <ulink>
        <xsl:attribute name="url"><xsl:value-of select="@xlink:href" /></xsl:attribute>
        <xsl:apply-templates />
      </ulink>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>


<!-- ####################
  INDEXTARGET element
     ####################-->
<xsl:template name="indextarget" match="indextarget">
  <indexterm>
    <xsl:if test="@entryLevel1">
      <primary><xsl:value-of select="@entryLevel1" /></primary>
    </xsl:if>
    <xsl:if test="@entryLevel2">
      <secondary><xsl:value-of select="@entryLevel2" /></secondary>
    </xsl:if>
    <xsl:if test="@entryLevel3">
      <tertiary><xsl:value-of select="@entryLevel3" /></tertiary>
    </xsl:if>
    <xsl:apply-templates />
    <xsl:if test="altref">
      <see><xsl:apply-templates select="altref" /></see>
    </xsl:if>
  </indexterm>
  <xsl:if test="(@entryLevel4!='') or (@entryLevel4!='') or (@entryLevel4!='') or (@entryLevel4!='') or (@entryLevel4!='') or (@entryLevel4!='')">
    <xsl:message>index term '<xsl:value-of select="@entryLevel1" />' has more than the 3 levels DocBook allows</xsl:message>
  </xsl:if> 
</xsl:template>

<!-- We need to collect indextarget entries in hidden subtrees! -->
<xsl:template match="indextarget" mode="hidden">
  <xsl:call-template name="indextarget" />
</xsl:template>

<!-- ####################
  HEADER element
     ####################-->
<xsl:template match="pageheader">
  <xsl:message>exporting page headers to DocBook is not supported</xsl:message>
  <xsl:comment> *** page header dropped *** </xsl:comment>
</xsl:template>


<!-- ####################
  FOOTER element
     ####################-->
<xsl:template match="pagefooter">
  <xsl:message>exporting page footers to DocBook is not supported</xsl:message>
  <xsl:comment> *** page footer dropped *** </xsl:comment>
</xsl:template>


<!-- ####################
  TOC element
     ####################-->
<xsl:template match="toc">
  <xsl:message>toc element skipped</xsl:message>
  <xsl:comment> *** explicit TOC dropped *** </xsl:comment>
</xsl:template>


<!-- ####################
  FORMTEXT element
     ####################-->
<xsl:template match="formtext">
  <phrase role="upcast-FORMTEXT">
    <xsl:apply-templates select="descendant::*[not( contains($ucBlockElements, name()) )] | descendant::text()" />
  </phrase>
</xsl:template>


<!-- ####################
  FORMCHECKBOX element
     ####################-->
<xsl:template match="formcheckbox">
  <phrase role="upcast-FORMCHECKBOX">
    <xsl:choose>
      <xsl:when test="@selectedValue='1'">
        <xsl:text>&#10003;</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>&#10065;</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </phrase>
</xsl:template>


<!-- ####################
  FORMDROPDOWN element
     ####################-->
<xsl:template match="formdropdown">
  <xsl:variable name="userchoice" select="@selectedValue" />
  <guimenuitem role="upcast-FORMDROPDOWN">
    <xsl:value-of select="formchoices/formchoice[@index = current()/@userchoice]" />
  </guimenuitem>
</xsl:template>


<!-- ####################
  TABLE element
     ####################-->
<xsl:template match="table">
  <!-- determine table type and -tag -->
  <xsl:variable name="tt">
    <xsl:choose>
      <xsl:when test="count(ancestor::footnote) > 0 
                      or count(ancestor::endnote) > 0
                      or not( preceding-sibling::*[position()=1 and name()='par' and contains($caption, @class)]
                              or
                              following-sibling::*[position()=1 and name()='par' and contains($caption, @class)] )
                     ">
        <xsl:text>informaltable</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>table</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:variable>
  
  <xsl:element name="{$tt}">
    <xsl:attribute name="frame"><xsl:value-of select="@frame" /></xsl:attribute>
    <xsl:if test="$tt = 'table'">
      <xsl:choose>
        <xsl:when test="following-sibling::*[position()=1 and name()='par' and contains($caption, @class)]">
          <title><xsl:value-of select="following-sibling::par[1]" /></title>
        </xsl:when>
        <xsl:when test="preceding-sibling::*[position()=1 and name()='par' and contains($caption, @class)]">
          <title><xsl:value-of select="preceding-sibling::par[1]" /></title>
        </xsl:when>
        <xsl:otherwise>
          <title>Untitled Table</title> <!-- required in content model! -->
        </xsl:otherwise>
      </xsl:choose>
    </xsl:if>
    <xsl:apply-templates />
  </xsl:element>
</xsl:template>


<!-- ####################
  TGROUP element
     ####################-->
<xsl:template match="tgroup">
  <tgroup cols="{@cols}">
    <xsl:apply-templates />
  </tgroup>
</xsl:template>


<!-- ####################
  COLSPEC element
     ####################-->
<xsl:template match="colspec">
  <colspec colnum="{@colnum}" colwidth="{@colwidth}" colname="{@colname}" />
</xsl:template>


<!-- ####################
  THEAD element
     ####################-->
<xsl:template match="thead">
  <thead>
    <xsl:apply-templates />
  </thead>
</xsl:template>


<!-- ####################
  TBODY element
     ####################-->
<xsl:template match="tbody">
  <tbody>
    <xsl:apply-templates />
  </tbody>
</xsl:template>


<!-- ####################
  ROW element
     ####################-->
<xsl:template match="row">
  <row>
    <xsl:apply-templates />
  </row>
</xsl:template>


<!-- ####################
  ENTRY element
     ####################-->
<xsl:template match="entry">
  <entry colsep="{@colsep}" rowsep="{@rowsep}" valign="{@valign}" colname="{@colname}">
    <xsl:if test="@namest"><xsl:attribute name="namest"><xsl:value-of select="@namest"/></xsl:attribute></xsl:if>
    <xsl:if test="@nameend"><xsl:attribute name="nameend"><xsl:value-of select="@nameend"/></xsl:attribute></xsl:if>
    <xsl:if test="@morerows"><xsl:attribute name="morerows"><xsl:value-of select="@morerows"/></xsl:attribute></xsl:if>

    <!-- Calculate halign attribute using heuristics: Set only when ALL paragraphs have the same alignment setting! -->
    <xsl:variable name="halign"><xsl:value-of select="child::par[1]/@text-align"/></xsl:variable>
    <xsl:choose>
      <xsl:when test="count(child::par) = count(child::par[@text-align = $halign]) and count(child::par) &gt; 0">
        <xsl:attribute name="align"><xsl:value-of select="$halign"/></xsl:attribute>
      </xsl:when>
      <xsl:otherwise>
        <!-- do not write the align attribute at all -->
      </xsl:otherwise>
    </xsl:choose>

    <xsl:apply-templates />

  </entry>
</xsl:template>


<!-- ####################
  nested TABLE element
     ####################-->
<xsl:template match="entry/table">
  <xsl:message>table dropped: nested tables not supported in CALS (use HTML table model)</xsl:message>
  <xsl:comment> ### complete table dropped: nested tables not supported in CALS (use HTML table model!) ### </xsl:comment>
</xsl:template>


<!-- ####################
  COLUMNBREAK element
     ####################-->
<xsl:template match="columnbreak">
  <xsl:comment> ***columnbreak in original document*** </xsl:comment>
</xsl:template>


<!-- ####################
  PAGEBREAK element
     ####################-->
<xsl:template match="pagebreak" name="pagebreak">
  <xsl:comment> ***pagebreak in original document*** </xsl:comment>
</xsl:template>


<!-- ####################
  LINEBREAK element
     ####################-->
<xsl:template match="linebreak">
  <!--changed 2004-11-17, bko-->
  <xsl:processing-instruction name="d-linebreak"/>
  <!-- <xsl:text>&#x2028;</xsl:text> -->
  <!-- Unicode explicit line break character - should we use it? -->
  <!--<xsl:text>&#xa;</xsl:text>-->
  <!-- for now, write simple newline character -->
</xsl:template>
                                                        

<!-- ####################
  OLE element - suppress!
     ####################-->
<xsl:template match="ole">
</xsl:template>

<!-- ####################
  OBJECT element
     ####################-->
<xsl:template match="object[@type='math']">
	<inlineequation>
		<xsl:choose>
		<xsl:when test="mml:math">
		  <!-- not yet...
			<xsl:apply-templates select="mml:math" />
			-->
		</xsl:when>
		<xsl:otherwise>
			<xsl:apply-templates select="image" />
		</xsl:otherwise>
		</xsl:choose>
	</inlineequation>
</xsl:template>

<xsl:template match="object">
	<xsl:apply-templates select="image" />
</xsl:template>


<!-- ####################
  MathML elements: copy!
     ####################-->
<xsl:template match="*[namespace-uri(.)='$mathmlnamespace']">
  <!-- not yet...
  <xsl:copy>
    <xsl:apply-templates />
  </xsl:copy>
  -->
</xsl:template>


<!-- ####################
  Hidden-Mode-DefaultHandler
     ####################-->
<xsl:template match="node()" mode="hidden">
  <xsl:apply-templates mode="hidden" />
</xsl:template>

<xsl:template match="*/text()" mode="hidden">
  <!-- skip -->
</xsl:template>

<!--
<xsl:template match="@*|node()">
  <xsl:copy>
    <xsl:apply-templates select="@*|node()"/>
  </xsl:copy>
</xsl:template>
-->

</xsl:stylesheet>
