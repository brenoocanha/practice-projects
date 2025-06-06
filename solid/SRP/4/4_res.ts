class CSVFileReader {
  read(file: string): any[] {
    console.log(`Lendo arquivo CSV: ${file}`);
    return [{ name: 'Alice', value: 100 }];
  }
}

class JSONExporter {
  export(data: any[]): string {
    const jsonReport = JSON.stringify(data);
    console.log(`Exportando relatório em JSON: ${jsonReport}`);
    return jsonReport;
  }
}

class ReportService {
  constructor(
    private csvFileReader: CSVFileReader,
    private jsonExporter: JSONExporter
  ) {}

  processFile(file: string): void {
    const data = this.csvFileReader.read(file);
    this.jsonExporter.export(data);
  }
}
