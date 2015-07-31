import luigi
from pyspark.sql import SQLContext
from pyspark import SparkContext
from pyspark.sql import Row, StructField, StructType, StringType, IntegerType
import pathutil
#from pathutil import PathSetTask

class GenderDist(luigi.Task):
   
    src = luigi.Parameter(is_list=True)
    include = luigi.Parameter(is_list=True, default=('*',))
    manifest = luigi.Parameter(default=None)
    required_tasks = None
    print(src)

    def output(self):
        return MockFile("SimpleTask", mirror_on_stderr=True)
 
    def requires(self):
        if self.required_tasks is None:
           self.required_tasks =  {'insert_source': self.insert_source_task}
        return self.required_tasks
    @property
    def insert_source_task(self):
        """Defines task that provides source of data for insertion."""
        return pathutil.PathSetTask(self.src, self.include, self.manifest)
        #

    def run(self):
        sc = SparkContext("local", "gender")
        sqlContext = SQLContext(sc)
        #StringType =(str, unicode)
        _out = self.output().open('w')
        #lines = sc.textFile("myUser.csv")
        #fobj = self.input().open("r")
        #lines = sc.textFile(fobj.name)
        print(type(self.required_tasks['insert_source'].output()))
        print(self.required_tasks['insert_source'])
        #print(self.input()['insert_source'].input())
        lines = sc.textFile("myUser.csv")
        parts = lines.map(lambda l: l.split(","))
        users = parts.map(lambda p: (p[0], p[1],p[2],p[3],p[4],p[5],p[6],p[7],
            p[8],p[9],p[10],p[11],p[12],p[13],p[14],p[15],p[16],p[17],p[18],p[19]))
        schemaString = "userId lmsUserId lmsName orgName name gender registrationDate emailId mothertounge highestEduDegree goals city state active firstAccesDate lastAccessDate allowCert yearOfBirth pincode aadharId"
        print(schemaString)
        _out.write(schemaString )
        fields = [StructField(field_name, StringType(), True) for field_name in schemaString.split()]
        schema = StructType(fields)
        #schemaUser = sqlContext.createDataFrame(users, schema)
        schemaUser = sqlContext.applySchema(users, schema)
        schemaUser.registerTempTable("users")
        results = sqlContext.sql("SELECT gender FROM users")
        genders = results.map(lambda p : (p,1))
        counts = genders.reduceByKey(lambda a, b: a + b) #.map(lambda t : ("Gender " + t(0) + " No " + t(1))).collect()
        for name in counts.collect():
            _out.write(str(name))
        _out.close()
 
 
if __name__ == '__main__':
    from luigi.mock import MockFile # import this here for compatibility with Windows
    # if you are running windows, you wouldn need --lock-pid-dir argument; modified run would look like
    # luigi.run(["--lock-pid-dir", "D:\\temp\\", "--local-scheduler"], main_task_cls=SimpleTask)
    luigi.run(["--local-scheduler"], main_task_cls=GenderDist)
