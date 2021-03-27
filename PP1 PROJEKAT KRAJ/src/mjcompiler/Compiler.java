package mjcompiler;

import java_cup.runtime.Symbol;
import mjcompiler.ast.Program;
import mjcompiler.ast.SyntaxNode;
import mjcompiler.utils.Log4JUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import rs.etf.pp1.mj.runtime.Code;

import java.io.*;

public class Compiler
{
    static {
        DOMConfigurator.configure(Log4JUtil.instance().findLoggerConfigFile());
        Log4JUtil.instance().prepareLogFile(Logger.getRootLogger());
    }
    public static void main(String[] args)
    {
        Logger log = Logger.getLogger(Compiler.class);
        Reader bufferReader = null;

        try{
            File source = new File(args[0]);
            log.info("Compiling source file :" + source.getAbsolutePath());

            bufferReader = new BufferedReader(new FileReader(source));

            Yylex lexer = new Yylex(bufferReader);

            MJParser parser = new MJParser(lexer);

            log.info("====================== Performing Syntax Check ======================");
            Symbol symbol = parser.parse();

            SyntaxNode root = (SyntaxNode)symbol.value;

            if (!(root instanceof  Program)) {
                log.error("Sintaksna greska! Prevodjenje se ne moze nastaviti");
                System.err.println("Sintaksna greska!");
                return;
            }

            Program program = (Program)root;

            log.info(program.toString(""));

            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();

            log.info("====================== Performing Semantic Check ======================");

            root.traverseBottomUp(semanticAnalyzer);

            if (semanticAnalyzer.hasError()){
                log.error("Semanticka greska! Prevodjenje se ne moze nastaviti");

                return;
            }
//
//            log.info("====================== Dumping Symol Table Content ======================");
//            tsdump();
//
            File destObjFile = new File(args[1]);
//
            if (destObjFile.exists()) {
                log.info("Destination file already exists. Its content is being deleted.");
                destObjFile.delete();
            }

            CodeGenerator codeGenerator = new CodeGenerator();

            log.info("====================== Performing Code Generation ======================");
            program.traverseBottomUp(codeGenerator);

            Code.write(new FileOutputStream(destObjFile));

            log.info("Compilation completed successfuly!");
        } catch (Exception e)
        {
            log.info("Compilation completed unsuccessfully");
        } finally{
            if (bufferReader != null) try { bufferReader.close(); } catch (IOException exc) { exc.printStackTrace(); }
        }
    }
    }
