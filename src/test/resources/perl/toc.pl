#!/usr/bin/perl 


my $infile = @ARGV[0];
my $destfolder = @ARGV[1];
my $outfile = @ARGV[2];

my $RegEx = qr"<\?xml version=\"1.0\" encoding=\"utf-8\"\?>";

open (INFILE, "< $infile") or die "Can't open $file: $!";
open (OUTFILE, "> $destfolder$outfile") or die "Can't write $file: $!";

@lines = <INFILE>;

# assumes indentation=yes in last xslt
while($line = shift @lines) {
		next if ( $line =~ m/\s*$RegEx\s*/o	);
		next if ( $line	=~ m/^\s*$/o );
#		$line =~ s!(<a href=)\"(#\w+)\"!$1\"\./index_html$2\"!o;
#		if no index.html is exposed by plone:
		$line =~ s!(<a href=)\"(#\w+)\"!$1\"$2\"!o;
		print OUTFILE $line;
	
}


close ( INFILE );
close ( OUTFILE );
