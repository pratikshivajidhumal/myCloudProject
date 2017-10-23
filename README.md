# myCloudProject
CMPE-281 Cloud Project code

1>University Name-: http://www.sjsu.edu/
2>Course: -Cloud Technologies.
3>Professor: - Sanjay Garje.
4>ISA: - Divyankitha Urs
5>Student-Pratik Dhumal (012457185)
6>LinkedIn profile- https://www.linkedin.com/in/pratik-dhumal-2937aa72/

Project Introduction: Introduction-File management system allows users to store, update, delete, view files using AWS cloud based web application. Users can upload any number of files or single up to 10 MB max file size. User can upload .txt, excel editable files. Users can update the file contents which can be downloaded immediately. User can check his file last file update time and date, upload time and date and update information. Whenever user wants to delete file it will be deleted. This application basically manages all the files using AWS cloud based components.
Login page
 
Registration page
 
After login multiple file to upload
 
Validation message for greater than 10 MB
 
Uploading bulk of 10 files at the same time
 
File uploaded successfully message after upload

Press view my files which will show current as well as new files
 
View of all the files with update, download, delete option with all the required information
 
Files uploaded in actual S3 bucket Mumbai
 
MYSQL workbench which is connected to remote MYSQL-RDS database entries 1 to many file relationship between user and file table ( user has many files foreign key to avoid redundancy)
 
Cross region replication in bucket of London
 
After file deletion step1 message
 
After file deletion step2 actual GUI compare both GUI auto adjusting dynamic table in jsp
 
After file download and open to check content

Original test 2 file after before update
 
After inserting some garbage data to same file
  
We will update the test2 file through update button
          
Test2 file at the button
                                
Updated file after update message and check updated time and date which is highlighted
 
Check that file in bucket which is updated successfully
 
Downloading updated file from gui
 
Check that file database file update time is stored there 
Project prerequisites
1>two S3 buckets with cross regions replication and transfer acceleration with 75 days+ 1year+ 1year glacier and delete after that policy is enabled.
2>CloudFront
3>Elastic beanstalk (To manage EC2 instances and load balance/ auto scaling)
4>MYSQL RDS instance with MYSQL workbench.
5>SNS activated with topic endpoint as email in appropriate region. 
6>JDK 1.8(Mandatory lower version will not work)
7>Glassfish 4.1.1
8>AWS SDK 1.9.2
9>Route53 with registered domain 
10>NetBeans IDE-8.2 you can directly import this maven project in NetBeans
11>CloudWatch with billing trigger at 0.1 USD
12>You can directly import project in NetBeans build with dependencies and click run it will open login page in local. You need to add your aws credentials in Credentials class and mysql connection id,user name and password that’s it. Project is ready to run
 (Note:-code for AWS credentials has been removed you need to replace your credentials and bucket names, regions at appropriate location
You need to add your own MYSQL database details)


