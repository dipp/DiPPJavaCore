#!/usr/bin/perl 


my $infile = @ARGV[0];
my $destfolder = @ARGV[1];
my $outfile = @ARGV[2];

my $RegEx = qr"<\?xml version=\"1.0";


open (INFILE, "< $infile") or die "Can't open $file: $!";
open (OUTFILE, "> $destfolder$outfile") or die "Can't write $file: $!";

@lines = <INFILE>;

# assumes indentation=yes in last xslt
while($line = shift @lines) {
		next if ( $line =~ m/\s*$RegEx\s*/o );
#		next if ( $line =~ m/^\s*$/o );
		next if ( $line =~ m!\s*<hr/>\s*! );
        $line =~ s/\xc2\xad//go;
        $line =~ s/\xc2\xa0/&nbsp;/go;
        $line =~ s!<a\s+(.+)\s*/>!<a $1></a>!goi;
		print OUTFILE $line;
	
}


close ( INFILE );
close ( OUTFILE );
